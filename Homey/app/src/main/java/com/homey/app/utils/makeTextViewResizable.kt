package com.homey.app.utils

import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.core.content.ContextCompat
import com.homey.app.R


fun makeTextViewResizable(
    tv: TextView,
    maxLine: Int, expandText: String, viewMore: Boolean
) {
    if (tv.tag == null) {
        tv.tag = tv.text
    }
    val vto = tv.viewTreeObserver
    vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        @Suppress("deprecation")
        override fun onGlobalLayout() {
            val obs = tv.viewTreeObserver
            obs.removeGlobalOnLayoutListener(this)
            if (maxLine == 0) {
                val lineEndIndex = tv.layout.getLineEnd(0)
                val text = tv.text.subSequence(
                    0,
                    lineEndIndex - expandText.length + 1,
                )
                    .toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        tv.text
                            .toString(), tv, maxLine, expandText,
                        viewMore
                    ), TextView.BufferType.SPANNABLE
                )
            } else if (maxLine > 0 && tv.lineCount >= maxLine) {
                val lineEndIndex = tv.layout.getLineEnd(maxLine - 1)
                val text = tv.text.subSequence(
                    0,
                    lineEndIndex - expandText.length + 1
                )
                    .toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        tv.text
                            .toString(), tv, maxLine, expandText,
                        viewMore
                    ), TextView.BufferType.SPANNABLE
                )
            } else {
                val lineEndIndex = tv.layout.getLineEnd(
                    tv.layout.lineCount - 1
                )
                val text = tv.text.subSequence(0, lineEndIndex)
                    .toString() + " " + expandText
                tv.text = text
                tv.movementMethod = LinkMovementMethod.getInstance()
                tv.setText(
                    addClickablePartTextViewResizable(
                        tv.text
                            .toString(), tv, lineEndIndex, expandText,
                        viewMore
                    ), TextView.BufferType.SPANNABLE
                )
            }
        }
    })
}

private fun addClickablePartTextViewResizable(
    strSpanned: String, tv: TextView, maxLine: Int,
    spanableText: String, viewMore: Boolean
): SpannableStringBuilder? {
    val ssb = SpannableStringBuilder(strSpanned)
    if (strSpanned.contains(spanableText)) {
        ssb.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    if (viewMore) {

//                        tv.layoutParams = tv.layoutParams
                        tv.setText(
                            tv.tag.toString(),
                            BufferType.SPANNABLE
                        )
                        tv.invalidate()
                        makeTextViewResizable(
                            tv, 2, "Read More",
                            true
                        )
                        tv.setTextColor(ContextCompat.getColor(tv.context, R.color.Orange))
                    }
                }
            }, strSpanned.indexOf(spanableText),
            strSpanned.indexOf(spanableText) + spanableText.length, 0
        )
    }
    return ssb
}