package com.example.test.ui.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.example.test.R
import com.example.test.databinding.FragmentAddProductBinding
import com.example.test.ui.base.BaseFragment
import com.example.test.ui.base.Const
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddProductFragment : BaseFragment<FragmentAddProductBinding>() {

    private var calendar = Calendar.getInstance()
    private var fromDateSet = false
    private var dateFrom: String? = null
    private var fromMonth: String? = null
    private var dateTo: String? = null

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?, attachedToParent: Boolean
    ): FragmentAddProductBinding {
        return FragmentAddProductBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() {
        setClickListener()

    }


    private fun setClickListener() = with(binding) {

        imageViewBack.setOnClickListener {
            popBackStack()
        }
        buttonAddItem.setOnClickListener {
            checkValidation()
        }

//        textInputEditTextTravelTitle.setOnKeyListener { _, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
//                if (textInputEditTextFromDate.text.toString().trim().isEmpty()) {
//                    hideKeyBoard()
//                    click(true)
//                } else if (editTextTODate.text.toString().trim().isEmpty()) {
//                    hideKeyBoard()
//                    click(false)
//                }
//            }
//            false
//        }
        //From Date
        textInputEditTextFromDate.setOnClickListener {
            click(true)
        }
        imageViewFromDate.setOnClickListener {
            click(true)
        }

        //To Date
        editTextTODate.setOnClickListener {
            click(false)
        }
        imageViewTODate.setOnClickListener {
            click(false)
        }
    }


    // Declare fromDate as a class-level variable
    private var fromDate: Long = 0

    private fun click(click: Boolean) {
        if (click) {
            showDatePickerDialog(click)
        } else {

            if (fromDateSet) {
                showDatePickerDialog(click)
            } else {
                showMessage(getString(R.string.please_select_from_date_first))
            }
        }
    }

    private fun showDatePickerDialog(isFromDate: Boolean) = with(binding) {
        val currentDate = Calendar.getInstance()
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.MONTH, 1)

        if (isFromDate) {
            calendar = Calendar.getInstance()
        }

        val date =
            OnDateSetListener { _, i, i1, i2 ->
                calendar.set(Calendar.YEAR, i)
                calendar.set(Calendar.MONTH, i1)
                calendar.set(Calendar.DAY_OF_MONTH, i2)
                val sDateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.US)

                if (isFromDate) {
                    val dDateFormat = SimpleDateFormat("dd", Locale.US)
                    val mDateFormat = SimpleDateFormat("MMM, yyyy", Locale.US)
                    dateFrom = dDateFormat.format(calendar.time)
                    fromMonth = mDateFormat.format(calendar.time)

                    if (sDateFormat.format(calendar.time) != textInputEditTextFromDate.text.toString()) {
                        editTextTODate.setText("")
                    }
                    fromDateSet = true
                    fromDate = calendar.timeInMillis
                    textInputEditTextFromDate.setText(sDateFormat.format(calendar.time))
                    click(false)
                } else {
                    val dDateFormat = SimpleDateFormat("dd", Locale.US)
                    dateTo = dDateFormat.format(calendar.time)
                    editTextTODate.setText(sDateFormat.format(calendar.time))
                    showKeyBoard()
                }
            }

        val datePickerDialog = DatePickerDialog(
            requireActivity(), date, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
        )

        datePickerDialog.window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_product_add
            )
        )

        if (isFromDate) {
            // Set minimum date
            datePickerDialog.datePicker.minDate = currentDate.timeInMillis
            // Set maximum date
            datePickerDialog.datePicker.maxDate = maxDate.timeInMillis
        } else {

            // Set minimum date
            datePickerDialog.datePicker.minDate = fromDate
            // Set maximum date
            datePickerDialog.datePicker.maxDate = maxDate.timeInMillis
        }

        datePickerDialog.show()
    }


    private fun checkValidation() = with(binding) {
        val travelTitle = textInputEditTextTravelTitle.text.toString().trim()
        val fromDateText = textInputEditTextFromDate.text.toString().trim()
        val toDateText = editTextTODate.text.toString().trim()
        val description = textInputEditTextDescription.text.toString().trim()
        val price = editTextPrice.text.toString().trim()

        if (travelTitle.isEmpty()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_travel_title)
            )
        } else if (fromDateText.isEmpty()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_date)
            )
            textInputEditTextFromDate.requestFocus()
        } else if (toDateText.isEmpty()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_to_date)
            )
            editTextTODate.requestFocus()
        } else if (description.isEmpty()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_a_description)
            )
            textInputEditTextDescription.requestFocus()
        } else if (price.isEmpty()) {
            showMessage(
                requireContext().getString(R.string.validation_please_enter_a_price)
            )
            editTextPrice.requestFocus()
        } else {


            val formattedFromDate = "$dateFrom -$dateTo $fromMonth"

            requireActivity().supportFragmentManager.setFragmentResult(
                Const.DATA, bundleOf(
                    Const.TRAVEL_TITLE to travelTitle,
                    Const.DATE to formattedFromDate,
                    Const.DESCRIPTION to description,
                    Const.PRICE to price
                )
            )
            popBackStack()
        }
    }
}