package com.starter.app.ui.home.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.starter.app.R
import com.starter.app.data.pojo.request.UpdateAddressRequest
import com.starter.app.databinding.AlertDialogLayoutBinding
import com.starter.app.databinding.FragmentProfileBinding
import com.starter.app.databinding.LoginBottomSheetFragmentBinding
import com.starter.app.di.DiConstants
import com.starter.app.exception.ApplicationException
import com.starter.app.ui.activity.AuthActivity
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.home.viewModel.LogOutViewModel
import com.starter.app.ui.home.viewModel.PatientDetailViewModel
import com.starter.app.ui.home.viewModel.UpdateAddressViewModel
import com.starter.app.ui.setGoal.fragment.SetGoalFragment
import com.starter.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {


    private val patientDetailViewModel: PatientDetailViewModel by viewModels()
    private val updateAddressViewModel: UpdateAddressViewModel by viewModels()
    private val logOutViewModel: LogOutViewModel by viewModels()

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextAddress)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_address))
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }
    private val isValidLocation: Boolean
        get() {
            return try {
                validator.submit(binding.edittextLocation)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_location))
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun bindData() {
        getPatientDetailApiCall()
        setClickListener()
//        Log.e("TAG", "bindData: ${session.user}")
//        Log.e("TAG", "bindData: ${session.userSession}")
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    @SuppressLint("SetTextI18n")
    private fun setData() = with(binding) {
        session.user?.let {

            Glide.with(imageViewCircular).load(it.profilePic).diskCacheStrategy(
                DiskCacheStrategy.ALL
            ).into(imageViewCircular)


            textViewName.text = it.name
            val gender = when (it.gender) {
                "F" -> "Female"
                "M" -> "Male"
                else -> ""
            }
            textViewAge.text = "${it.patientAge} Years, $gender "
            textViewContactNumber.text = it.contactNo
            textViewEmail.text = it.email
            edittextAddress.setText("${it.address}, ")
            edittextLocation.setText("${it.city}, ${it.state}")
            textviewIndication.text = it.medicalConditionName?.get(0)?.medicalConditionName
        }
    }

    private fun getPatientDetailApiCall() {
        DiConstants.showProgressDialog(requireActivity())
        patientDetailViewModel.patientDetails()
    }


    private fun observeLiveData() {
        patientDetailViewModel.patientDetailLiveData.observe(
            this, onChange = {
                DiConstants.hideProgressDialog()
                session.user = it.data
                setData()
            }
        )

        updateAddressViewModel.updateAddressLiveData.observe(
            this, onChange = {
                DiConstants.hideProgressDialog()
                session.user = it.data
            }
        )
        logOutViewModel.logOutLiveData.observe(
            this, onChange = {
                DiConstants.hideProgressDialog()
                appPreferences.clearAll()
                navigator.loadActivity(AuthActivity::class.java).byFinishingAll().start()
            }, onError = {
                Log.e("TAG", "observeLiveData: $it")
                return@observe true
            }
        )
    }

    private fun setClickListener() = with(binding) {
        buttonUpdate.setOnClickListener {
            navigator.load(SetGoalFragment::class.java).replace(true)
        }

        textviewLogOut.setOnClickListener {
            showAlertDialog()
        }

        imageViewEdit.setOnClickListener {
            navigator.load(EditProfileFragment::class.java).replace(true)
        }

        buttonEditLocation.setOnClickListener {
            if (buttonEditLocation.text.toString() == getString(R.string.edit)) {
                edittextLocation.setPadding(
                    resources.getDimensionPixelSize(R.dimen._10dp),
                    resources.getDimensionPixelSize(R.dimen._10dp),
                    resources.getDimensionPixelSize(R.dimen._10dp),
                    resources.getDimensionPixelSize(R.dimen._10dp),
                )
                edittextLocation.isEnabled = true
                edittextLocation.requestFocus()
                showKeyBoard()
                buttonEditLocation.text = getString(R.string.button_save)
            } else {
                if (isValidLocation) {
                    edittextLocation.setPadding(
                        resources.getDimensionPixelSize(R.dimen._0dp),
                        resources.getDimensionPixelSize(R.dimen._0dp),
                        resources.getDimensionPixelSize(R.dimen._0dp),
                        resources.getDimensionPixelSize(R.dimen._0dp),
                    )
                    edittextLocation.isEnabled = false
                    hideKeyBoard()
                    buttonEditLocation.text = getString(R.string.edit)
                }
            }
        }

        buttonEditAddress.setOnClickListener {
            if (buttonEditAddress.text.toString() == getString(R.string.edit)) {
                edittextAddress.setPadding(
                    resources.getDimensionPixelSize(R.dimen._10dp),
                    resources.getDimensionPixelSize(R.dimen._10dp),
                    resources.getDimensionPixelSize(R.dimen._10dp),
                    resources.getDimensionPixelSize(R.dimen._10dp),
                )
                edittextAddress.isEnabled = true
                edittextAddress.requestFocus()
                showKeyBoard()
                buttonEditAddress.text = getString(R.string.button_save)
            } else {
                if (isValid) {
                    edittextAddress.setPadding(
                        resources.getDimensionPixelSize(R.dimen._0dp),
                        resources.getDimensionPixelSize(R.dimen._0dp),
                        resources.getDimensionPixelSize(R.dimen._0dp),
                        resources.getDimensionPixelSize(R.dimen._0dp),
                    )
                    edittextAddress.isEnabled = false
                    buttonEditAddress.text = getString(R.string.edit)
                    hideKeyBoard()
                    updateAddressApiCall()
                }
            }
        }
    }
private fun showAlertDialog() {
        val alertDialogLayoutBinding: AlertDialogLayoutBinding =
            AlertDialogLayoutBinding.inflate(layoutInflater)
        val alertDialog = AlertDialog.Builder(requireActivity(), R.style.CustomAlertDialog)
            .create()

        alertDialog.setView(alertDialogLayoutBinding.root)

        alertDialogLayoutBinding.buttonLogout.setOnClickListener {
            alertDialog.dismiss()
            callLogOutApi()
        }

        alertDialogLayoutBinding.buttonCancle.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }

    private fun updateAddressApiCall() {
        DiConstants.showProgressDialog(requireActivity())
        val request = UpdateAddressRequest(
            address = binding.edittextAddress.text.toString()
        )

        updateAddressViewModel.updateAddress(request)
    }

    private fun callLogOutApi() {
        DiConstants.showProgressDialog(requireActivity())
        logOutViewModel.logOut()
    }


}