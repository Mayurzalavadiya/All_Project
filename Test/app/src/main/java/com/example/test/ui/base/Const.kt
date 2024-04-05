package com.example.test.ui.base

import android.content.Context
import android.icu.text.Transliterator.Position
import android.widget.Toast

object Const {
    const val IS_LOGIN = "is login"
    const val DATA = "data"
    const val TITLE = "title"
    const val IMAGE = "image"
    const val DESCRIPTION = "description"
    const val LOCATION = "location"
    const val POSITION = "position"
    const val TRAVEL_TITLE = "travel title"
    const val DATE = "from date"
    const val PRICE = "price"

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
