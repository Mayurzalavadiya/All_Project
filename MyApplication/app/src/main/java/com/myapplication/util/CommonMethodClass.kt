package com.myapplication.util

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class CommonMethodClass {
    constructor(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    constructor(view: View?, message: String?) {
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_SHORT).show()
    }

    constructor(context: Context, nextClass: Class<*>?) {
        val intent = Intent(context, nextClass)
        context.startActivity(intent)
    }
}