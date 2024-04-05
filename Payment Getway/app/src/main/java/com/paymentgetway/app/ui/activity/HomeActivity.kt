package com.paymentgetway.app.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.paymentgetway.app.R
import com.paymentgetway.app.databinding.HomeActivityBinding
import com.paymentgetway.app.exception.ApplicationException
import com.paymentgetway.app.ui.base.BaseActivity
import com.paymentgetway.app.ui.home.fragment.QrScannerFragment
import com.paymentgetway.app.ui.viewmodel.GetCustomerViewModel
import com.paymentgetway.app.ui.viewmodel.GetEphemeralViewModel
import com.paymentgetway.app.ui.viewmodel.GetPaymentViewModel
import com.paymentgetway.app.utils.CustomDialogFragment
import com.paymentgetway.app.utils.Validator
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.OrderRequest
import com.paypal.checkout.order.PurchaseUnit
import com.permissionx.guolindev.PermissionX
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : BaseActivity(), PaymentResultListener {


    private lateinit var binding: HomeActivityBinding
    private lateinit var customerId: String
    private lateinit var ephemeralKey: String
    private lateinit var clientSecret: String

    private val publishKey =
        "pk_test_51OmsDCSDKMfz2gaJaI6f1o0TDV8cewQFQqzDo9nsiJC0MV8TecXv9czy9nGyE5k66fjFZvEl1pZGrFWvvp0OY3nH00CSfjblzY"

    private lateinit var paymentSheet: PaymentSheet

    private val getCustomerViewModel: GetCustomerViewModel by viewModels()
    private val getEphemeralViewModel: GetEphemeralViewModel by viewModels()
    private val getPaymentViewModel: GetPaymentViewModel by viewModels()


    private val GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
    private var GOOGLE_PAY_REQUEST_CODE = 123
    private var amount: String? = null
    var name = "Mayur Zalavadiya"
    private var upiId = "mayurzalavadiya1999-2@okhdfcbank"
    private var transactionNote = "pay test"
    private var status: String? = null
    private var uri: Uri? = null


    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.editAmount)
                    .checkEmpty()
                    .errorMessage("Please enter amount")
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeLiveData()
        setClickListener()
        Checkout.preload(this)
        setupPaymentSheet()
        callCustomerApi()

    }


    private fun observeLiveData() {
        getCustomerViewModel.customerLiveData.observe(this, { responseBody ->
            responseBody?.let {
                Log.e("TAG", "observeLiveData: $it")
                customerId = it.id.toString()
                callEphemeralKey()
            }
        })

        getEphemeralViewModel.ephemeralLiveData.observe(this, { responseBody ->
            responseBody?.let {
                Log.e("TAG", "observeLiveData: $it")
                ephemeralKey = it.id.toString()
                callPayment()
            }
        })

        getPaymentViewModel.paymentLiveData.observe(this, { responseBody ->
            responseBody?.let {
                Log.e("TAG", "observeLiveData: $it")
                clientSecret = it.clientSecret.toString()
//                showMessage("Payment by Stripe")
            }
        })
    }


    private fun setupPaymentSheet() {
        PaymentConfiguration.init(this, publishKey)
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)
    }

    private fun paymentFlow() {
        paymentSheet.presentWithPaymentIntent(
            clientSecret,
            PaymentSheet.Configuration(
                "Mayur",
                PaymentSheet.CustomerConfiguration(customerId, ephemeralKey)
            )

        )
    }

    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Canceled -> {
                showMessage("Canceled")
            }

            is PaymentSheetResult.Failed -> {
                showMessage("Error: ${paymentSheetResult.error}")
            }

            is PaymentSheetResult.Completed -> {
                // Display for example, an order confirmation screen
                showMessage("Completed")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setClickListener() = with(binding) {
        buttonPayment.setOnClickListener {
            if (isValid) {
                savePayments(editAmount.text.toString().trim().toInt())
            }
        }

        buttonStripe.setOnClickListener {
            paymentFlow()

        }



        googlePayButton.setOnClickListener {
            if (isValid) {
                amount = binding.editAmount.text.toString()
                uri = getUpiPaymentUri(name, upiId, transactionNote, amount)
                payWithGPay()
            }
        }

        buttonQrScanner.setOnClickListener {
            PermissionX.init(this@HomeActivity)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES,Manifest.permission.SEND_SMS)
                .setDialogTintColor(Color.parseColor("#1972e8"), Color.parseColor("#8ab6f5"))

                .onExplainRequestReason { scope, deniedList, beforeRequest ->
//                    val message = "PermissionX needs following permissions to continue"
//                    scope.showRequestReasonDialog(deniedList, message, "Allow", "Deny")
                    val message = "Please allow the following permissions in settings"
                    val dialog = CustomDialogFragment(message, deniedList)
                    scope.showRequestReasonDialog(dialog)
                }
                .onForwardToSettings { scope, deniedList ->
                    val message = "Please allow following permissions in settings"
                    scope.showForwardToSettingsDialog(deniedList, message, "Allow", "Deny")
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        showMessage("All permissions are granted")
                        loadActivity(
                            IsolatedActivity::class.java,
                            QrScannerFragment::class.java
                        ).start()
                    } else {
                        showMessage("The following permissions are denied：$deniedList")
                    }
                }
        }

        buttonPaypal.setup(
            createOrder =
            CreateOrder
            { createOrderActions ->

                val order =
                    OrderRequest(
                        intent = OrderIntent.CAPTURE,
                        appContext = AppContext(userAction = UserAction.PAY_NOW),
                        purchaseUnitList =
                        listOf(
                            PurchaseUnit(
                                amount =
                                Amount(
                                    currencyCode = CurrencyCode.INR,
                                    value = editAmount.text.toString()
                                )
                            )
                        )
                    )
                createOrderActions.create(order)
            },
            onApprove =
            OnApprove
            { approval ->
                approval.orderActions.capture { _ ->
                    showMessage("Payment Success")
                    Log.e("TAG", "setClickListener: ${approval.data}")

                }
            },
            onCancel = OnCancel
            {
                showMessage("Payment canceled")
            },
            onError = OnError
            { errorInfo ->
                Log.e("TAG", "setClickListener: $errorInfo")
                showMessage("Payment Error")

            }
        )
    }



    private fun callCustomerApi() {
        getCustomerViewModel.customer()
    }

    private fun callEphemeralKey() {
        getEphemeralViewModel.ephemeral(customerId)
    }

    private fun callPayment() {
        getPaymentViewModel.payment(
            customerId,
        )
    }

    private fun savePayments(amount: Int) {

        val checkout = Checkout()

        checkout.setKeyID("rzp_test_lyVg4EbhAATCjE")

        try {

            val options = JSONObject()

            options.put("name", "Razorpay")

            options.put("description", "Test Payment")

            options.put(
                "image",
                "https://images.unsplash.com/photo-1595064085577-7c2ef98ec311?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            )
            options.put("theme.color", getColor(R.color.colorPrimary))

            options.put("currency", "INR")

            options.put("amount", amount * 100)


            val retryObj = JSONObject()

            retryObj.put("enabled", true)

            retryObj.put("max_count", 4)

            options.put("retry", retryObj)

            val preFill = JSONObject()
            preFill.put("email", " ")
            preFill.put("contact", " ")
            options.put("prefill", preFill)

            checkout.open(this, options)

        } catch (e: Exception) {
            showMessage(e.message.toString())

            e.printStackTrace()

        }


    }


    override fun onPaymentSuccess(p0: String?) {
        binding.paymentStatus.text = p0

        binding.paymentStatus.setTextColor(Color.BLUE)
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        binding.paymentStatus.text = "Payment Not Sucessfull"

        binding.paymentStatus.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
    }


    private fun isAppInstalled(context: Context, packageName: String): Boolean {
        return try {
            context.packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun getUpiPaymentUri(
        name: String,
        upiId: String,
        transactionNote: String,
        amount: String?
    ): Uri? {
        return Uri.Builder()
            .scheme("upi")
            .authority("pay")
            .appendQueryParameter("pa", upiId)
            .appendQueryParameter("pn", name)
            .appendQueryParameter("tn", transactionNote)
            .appendQueryParameter("am", amount)
            .appendQueryParameter("cu", "INR")
            .build()
    }


    private fun payWithGPay() {
        if (isAppInstalled(this, GOOGLE_PAY_PACKAGE_NAME)) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(uri)
            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME)
            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE)
        } else {
            Toast.makeText(this, "Please Install GPay", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            status = data.getStringExtra("Status")!!.lowercase(Locale.getDefault())
        }
        if (RESULT_OK == resultCode && status == "success") {
            Toast.makeText(this, "Transaction Successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Transaction Failed", Toast.LENGTH_SHORT).show()
        }
    }

}


