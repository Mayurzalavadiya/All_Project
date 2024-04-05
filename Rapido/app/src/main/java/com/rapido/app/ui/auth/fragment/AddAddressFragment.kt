package com.rapido.app.ui.auth.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.rapido.app.data.pojo.request.UpdateAddressRequest
import com.rapido.app.data.pojo.response.AddressResponse
import com.rapido.app.di.DiConstants
import com.rapido.app.exception.ApplicationException
import com.rapido.app.ui.auth.viewmodel.GetAddressViewModel
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.utils.Validator
import com.rapido.app.R
import com.rapido.app.databinding.FragmentAddAddressBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddAddressFragment : BaseFragment<FragmentAddAddressBinding>() {

    var addressType = "Home"

    @Inject
    lateinit var validator: Validator

    private val getAddressViewModel: GetAddressViewModel by viewModels()


    private var address: AddressResponse? = null

    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextAddress)
                    .checkEmpty()
                    .errorMessage("Please enter address")
                    .check()

                validator.submit(binding.edittextAddressDes)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_address_description))
                    .check()

                validator.submit(binding.edittextCity)
                    .checkEmpty().errorMessage(getString(R.string.validation_please_enter_city))
                    .check()

                validator.submit(binding.edittextStreetAddress)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_street_address))
                    .check()
                validator.submit(binding.edittextState)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_state))
                    .check()
                validator.submit(binding.edittextZipCode)
                    .checkEmpty()
                    .errorMessage(getString(R.string.validation_please_enter_zipcode))
                    .checkMaxDigits(6)
                    .errorMessage(getString(R.string.validation_please_enter_valid_zipcode))
                    .checkMinDigits(6)
                    .errorMessage(getString(R.string.validation_please_enter_valid_zipcode))
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    private fun observeLiveData() {
        getAddressViewModel.updateAddressLiveData.observe(this, {
            toggleLoader(false)
            showMessage(it.message)
            navigator.goBack()
        }, {
            toggleLoader(false)
            return@observe true
        })
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentAddAddressBinding {
        return FragmentAddAddressBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        getArgument()
        setData()
        setSpinner()
        setClickListener()
    }

    private fun setData() = with(binding) {
        address?.let {
            edittextAddress.setText(it.address)
            edittextStreetAddress.setText(it.streetAddress)
            edittextAddressDes.setText(it.description)
            edittextCity.setText(it.city)
            edittextState.setText(it.state)
            edittextZipCode.setText(it.zipcode.toString().split(".")[0])
            addressType = it.addressType.toString()
        }
    }

     private fun getArgument() {
             address = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // TIRAMISU
                 arguments?.getParcelable(DiConstants.ADDRESS)
             } else {
                 arguments?.getParcelable(DiConstants.ADDRESS)
             }
         }

    private fun setClickListener() = with(binding) {

        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
        buttonAdd.setOnClickListener {
            if (isValid) {
                apiCall()
            }
        }

        spinnerType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                addressType = parent.getItemAtPosition(position).toString()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                addressType = parent.getItemAtPosition(0).toString()
            }
        }
    }

    private fun apiCall() = with(binding) {
        toggleLoader(true)
        val request = UpdateAddressRequest(
            requestType = "add",
            address = edittextAddress.text.toString().trim(),
            streetAddress = edittextStreetAddress.text.toString().trim(),
            description = edittextAddressDes.text.toString().trim(),
            city = edittextCity.text.toString().trim(),
            state = edittextState.text.toString().trim(),
            zipcode = edittextZipCode.text.toString().trim(),
            addressType = addressType,
            latitude = "123.123",
            longitude = "123.123",
        )
        address?.let {
            request.apply {
                requestType = "edit"
                addressId = it.id
            }
        }
        getAddressViewModel.updateAddress(request)
    }

    private fun setSpinner() = with(binding) {

        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.TypeofAddress,
            R.layout.custom_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerType.adapter = adapter
            val spinnerPosition: Int = adapter.getPosition(addressType)
            spinnerType.setSelection(spinnerPosition)
        }

    }
}