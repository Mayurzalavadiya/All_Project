package com.homey.app.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun numberFormat(editText: EditText) {
    editText.addTextChangedListener(object :
        TextWatcher {
        var isBackspaceClicked = false

        override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
            isBackspaceClicked = after < count
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (!isBackspaceClicked) {
                val digits = StringBuilder()
                val phone = StringBuilder()
                val chars: CharArray = editText.text.toString().toCharArray()
                for (x in chars.indices) {
                    if (Character.isDigit(chars[x])) {
                        digits.append(chars[x])
                    }
                }

                if (digits.toString().length >= 3) {
                    var countryCode = String()
                    countryCode += "" + digits.toString().substring(0, 3) + "-"
                    phone.append(countryCode)
                    if (digits.toString().length >= 6) {
                        var regionCode = String()
                        regionCode += digits.toString().substring(3, 6) + "-"
                        phone.append(regionCode)
                        if (digits.toString().length >= 10) {
                            phone.append(digits.toString().substring(6, 10))
                        } else {
                            phone.append(digits.toString().substring(6))
                        }
                    } else {
                        phone.append(digits.toString().substring(3))
                    }
                    editText.removeTextChangedListener(this)
                    editText.setText(phone.toString())
                    editText.setSelection(editText.text.toString().length)
                    editText.addTextChangedListener(this)
                } else {
                    return
                }
            }
        }
    })
}
