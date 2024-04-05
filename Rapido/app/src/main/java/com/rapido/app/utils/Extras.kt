package com.rapido.app.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText

fun setPasswordToggle(editText: EditText,view: View) {
    view.setOnClickListener {


        if (editText.transformationMethod.equals(
                PasswordTransformationMethod.getInstance()
            )
        ) {
            editText.transformationMethod =
                HideReturnsTransformationMethod.getInstance()


        } else {
            editText.transformationMethod =
                PasswordTransformationMethod.getInstance()

        }

        editText.setSelection(editText.text.toString().length)
    }
}