package com.homey.app.utils

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor

fun TextView.setDrawableColor(color: Int) {
          compoundDrawables.filterNotNull().forEach {
              it.colorFilter = PorterDuffColorFilter(getColor(context, color), PorterDuff.Mode.SRC_IN)
        }
   }