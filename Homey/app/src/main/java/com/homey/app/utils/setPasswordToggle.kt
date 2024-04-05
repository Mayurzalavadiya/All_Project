package com.homey.app.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
import com.homey.app.R

fun setPasswordToggle(editText: EditText, view: AppCompatImageView) {

        view.setOnClickListener {
            if (editText.transformationMethod.equals(
                    PasswordTransformationMethod.getInstance()
                )
            ) {
                editText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                view.setImageResource(R.drawable.eye_slash_shoe_icon)

            } else {
                editText.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                view.setImageResource(R.drawable.eye_slash_hide_iocn)
            }

            editText.setSelection(editText.text.toString().length)
        }
    }