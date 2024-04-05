package com.starter.app.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.starter.app.R
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.UpdateProfileRequest
import com.starter.app.databinding.FragmentEditProfileBinding
import com.starter.app.di.DiConstants
import com.starter.app.exception.ApplicationException
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.home.viewModel.UpdateProfileViewModel
import com.starter.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {

//    private var calendar = Calendar.getInstance()

    @Inject
    lateinit var validator: Validator

    private var patientDetail: User? = null

    private val updateProfileViewModel: UpdateProfileViewModel by viewModels()

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextName)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_name))
                    .check()

                validator.submit(binding.edittextEmail)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_email))
                    .checkValidEmail()
                    .errorMessage(getString(R.string.validation_please_enter_valid_email_address))
                    .check()

                validator.submit(binding.edittextDateOfBirth)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_date_of_birth))
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentEditProfileBinding {
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.Purple)
        return FragmentEditProfileBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun bindData() {
        patientDetail = session.user
        setData()
        setClickListener()
    }

    private fun setData() = with(binding) {
        patientDetail?.let {
            it.profilePic?.let { it1 ->
                Glide.with(imageViewCircular).load(it1).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                )
                    .into(imageViewCircular)
            }
            it.name?.let { it1 -> edittextName.setText(it1) }
            it.email?.let { it1 -> edittextEmail.setText(it1) }
            it.dob?.let { it1 -> edittextDateOfBirth.setText(convertDateFormat(it1)) }
        }
    }

    private fun observeLiveData() {
        updateProfileViewModel.updateProfileLiveData.observe(
            this, onChange = {
                DiConstants.hideProgressDialog()
                session.user = it.data
                navigator.goBack()
            }
        )

    }

    private fun setClickListener() = with(binding) {

        buttonGetStart.setOnClickListener {
            if (isValid) {
                updateApiCall()
            }
        }
        imageViewBackArrow.setOnClickListener {
            navigator.goBack()
        }
//        edittextDateOfBirth.setOnClickListener {
//            hideKeyBoard()
//            showDatePickerDialog()
//        }
    }

    private fun updateApiCall() {
        DiConstants.showProgressDialog(requireActivity())
        val request = UpdateProfileRequest(
            email = binding.edittextEmail.text.toString(),
            name = binding.edittextName.text.toString()
        )
        updateProfileViewModel.updateProfile(request)
    }

    /*private fun showDatePickerDialog() = with(binding) {
        val maxDate = Calendar.getInstance()

        calendar = Calendar.getInstance()


        val date =
            DatePickerDialog.OnDateSetListener { _, i, i1, i2 ->
                calendar.set(Calendar.YEAR, i)
                calendar.set(Calendar.MONTH, i1)
                calendar.set(Calendar.DAY_OF_MONTH, i2)
                val sDateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.US)
                binding.edittextDateOfBirth.setText(sDateFormat.format(calendar.time))
            }

        val datePickerDialog = DatePickerDialog(
            requireActivity(), R.style.DatePickerDialog, date, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = maxDate.timeInMillis

        datePickerDialog.show()
    }*/


    private fun convertDateFormat(inputDate: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        val outputFormat = SimpleDateFormat("dd/mm/yyyy", Locale.US)

        val date = inputFormat.parse(inputDate)
        return date?.let { outputFormat.format(it) }
    }
}