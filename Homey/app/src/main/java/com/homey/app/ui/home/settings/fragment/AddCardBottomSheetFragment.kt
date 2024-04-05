package com.homey.app.ui.home.settings.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.homey.app.R
import com.homey.app.databinding.FragmentAddCardBottomSheetBinding
import com.homey.app.exception.ApplicationException
import com.homey.app.ui.base.BaseBottomSheet
import com.homey.app.ui.home.settings.model.AddMoneyData
import com.homey.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.KFunction1

@AndroidEntryPoint
class AddCardBottomSheetFragment : BaseBottomSheet<FragmentAddCardBottomSheetBinding>() {

    @Inject
    lateinit var validator: Validator
    lateinit var onCLick: KFunction1<AddMoneyData, Unit>

    val cardRegex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
            "(?<mastercard>5[1-5][0-9]{14})|" +
            "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
            "(?<amex>3[47][0-9]{13})|" +
            "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
            "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$"

    val cvvRegex = "^[0-9]{3,4}$"
    val nameRegex = "^\\p{L}+( \\p{L}+)*(-\\p{L}+)*$"
    val expDateRegex = "^(0[1-9]|1[0-2])/([0-9]{2})$"


    private val isValid: Boolean
        get() {
            return try {
                validator.submit(binding.edittextCardNumber)
                    .checkEmpty()
                    .errorMessage("Please enter card number")
                    .matchPatter(cardRegex).errorMessage("Please enter valid card number")
                    .check()

                validator.submit(binding.edittextNameonCard)
                    .checkEmpty()
                    .errorMessage("Please enter card name")
                    .matchPatter(nameRegex)
                    .errorMessage("Please enter valid card name")
                    .check()

                validator.submit(binding.edittextExpDate)
                    .checkEmpty()
                    .errorMessage("Please enter exp. date")
                    .matchPatter(expDateRegex)
                    .errorMessage("Please enter valid exp. date")
                    .check()
                validator.submit(binding.edittextCVV)
                    .checkEmpty()
                    .errorMessage("Please enter cvv")
                    .matchPatter(cvvRegex)
                    .errorMessage("Please enter valid cvv")
                    .check()
                true
            } catch (e: ApplicationException) {
                showMessage(e.message)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentAddCardBottomSheetBinding {
        return FragmentAddCardBottomSheetBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setClickListener()
    }

    private fun setClickListener() = with(binding) {
        buttonAdd.setOnClickListener {
            if (isValid) {
                val data = AddMoneyData(
                    edittextCardNumber.text.toString(),
                    edittextNameonCard.text.toString(),
                    edittextExpDate.text.toString(),
                    edittextCVV.text.toString()
                )
                onCLick(data)
                dismiss()
            }
        }
    }

    override fun destroyViewBinding() {
        dismiss()
    }

    fun callBack(kFunction0: KFunction1<AddMoneyData, Unit>) {
        this.onCLick = kFunction0
    }
}