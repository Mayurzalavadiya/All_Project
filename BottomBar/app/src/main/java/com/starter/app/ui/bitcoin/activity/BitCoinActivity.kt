package com.starter.app.ui.bitcoin.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.starter.app.R
import com.starter.app.data.pojo.response.BitCoinResponse
import com.starter.app.databinding.ActivityBitCoinBinding
import com.starter.app.ui.base.BaseActivity
import com.starter.app.ui.bitcoin.viewmodel.BitcoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class BitCoinActivity : BaseActivity() {

    private lateinit var binding: ActivityBitCoinBinding

    private val viewModel: BitcoinViewModel by viewModels()

    private var isSetData = false

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun findFragmentPlaceHolder(): Int = 0

    override fun createViewBinding(): View {
        binding = ActivityBitCoinBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set system UI visibility to light theme and background color to white
        setSystemUiVisibilityLightTheme()

        apiCall()
//        setClickListener()
        observeLiveData()
    }

    private fun setSystemUiVisibilityLightTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val window = window
            val controller = window.insetsController
            //set statusBar color
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            // Set the appearance to light theme
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
    }


    private fun apiCall() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {

            viewModel.bitCoinList()

            handler.postDelayed(runnable, 60 * 1000)

        }
        handler.postDelayed(runnable, 1000)
    }

    private fun observeLiveData() {
        viewModel.bitCoinLiveData.observe(this) {
            it?.let {
                setData(it)
            }
        }
    }

    private fun setData(it: BitCoinResponse) = with(binding) {
        //different

        if (isSetData) {
            different(
                textViewDifferent,
                textViewUsdRate.text.toString().toDouble(),
                it.bpi?.uSD?.rateFloat
            )
            different(
                textViewGBPDifferent,
                textViewGBPRate.text.toString().toDouble(),
                it.bpi?.gBP?.rateFloat
            )
            different(
                textViewEURDifferent,
                textViewEURRate.text.toString().toDouble(),
                it.bpi?.eUR?.rateFloat
            )
        }

        textViewBitcoin.text = it.chartName

        textViewUpdatedDateTime.text = it.time?.updated?.let { it1 -> convertDateFormat(it1) }

        //USD
        textviewUsd.text = it.bpi?.uSD?.code
        textViewUsdDescription.text = it.bpi?.uSD?.description
        textViewUsdRate.text = it.bpi?.uSD?.rateFloat.toString()
        //GBP
        textviewGBP.text = it.bpi?.gBP?.code
        textViewGBPDescription.text = it.bpi?.gBP?.description
        textViewGBPRate.text = it.bpi?.gBP?.rateFloat.toString()
        //EUR
        textviewEUR.text = it.bpi?.eUR?.code
        textViewEURDescription.text = it.bpi?.eUR?.description
        textViewEURRate.text = it.bpi?.eUR?.rateFloat.toString()

        isSetData = true
    }

//    private fun setClickListener() = with(binding) {
//        buttonFetch.setOnClickListener {
//            viewModel.bitCoinList()
//        }
//    }

    private fun convertDateFormat(inputDate: String): String? {
        val inputFormat = SimpleDateFormat("MMM dd, yyyy HH:mm:ss z", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("dd MMM yyyy 'at' hh:mm a", Locale.US)

        val date = inputFormat.parse(inputDate)
        return date?.let { outputFormat.format(it) }
    }

    @SuppressLint("SetTextI18n")
    fun different(textViewDifferent: AppCompatTextView, oldPrice: Double, newPrice: Double?) {
        newPrice?.let {
            val difference = newPrice - oldPrice

            val formattedDifference = DecimalFormat("#.##").format(difference)

            if (difference > 0) {
                textViewDifferent.text = "+$formattedDifference"
                textViewDifferent.setTextColor(ContextCompat.getColor(this, R.color.green))
            } else if (difference < 0) {
                textViewDifferent.text = formattedDifference
                textViewDifferent.setTextColor(ContextCompat.getColor(this, R.color.red))
            }
        }
    }
}
