package com.rapido.app.di

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.rapido.app.R

object DiConstants {
    const val PLACEHOLDER = "placeholder"
    const val CACHE = "cache"
    const val AES_KEY = "aes-key"
    const val API_KEY = "api-key"
    const val HEADER = "header"
    const val PRE_VALIDATION = "pre_validation"
    const val AES = "aes"


    const val IS_LOGIN = "is login"
    const val LIST_DATA = "list data"
    const val SEND_LIST_DATA = "list data"
    const val MOBILE_NUMBER = "mobile number"
    const val ADDRESS = "address "


    private var progressDialog: Dialog? = null

    fun showProgressDialog(context: Context) {
        try {
//            hideProgressDialog()
            val dialog = Dialog(context)
            progressDialog = dialog
            dialog.setCancelable(false)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.custom_progress_view)
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog.show()
        } catch (_: Exception) {

        }
    }

    fun hideProgressDialog() {
        try {
            if (progressDialog != null) {
                if (progressDialog!!.isShowing) {
                    progressDialog!!.dismiss()
                }
            }

        } catch (_: Exception) {
        }
        progressDialog = null
    }
}