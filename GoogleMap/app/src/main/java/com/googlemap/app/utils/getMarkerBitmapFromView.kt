package com.googlemap.app.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.googlemap.app.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import javax.annotation.Nullable

fun getMarkerBitmapFromView(context: Context, imageUrl: String, callback: (Bitmap) -> Unit) {
    // Inflate the custom marker layout
    val customMarkerView: View =
        LayoutInflater.from(context).inflate(R.layout.view_custom_marker, null)

    // Find the ImageView in the custom marker layout
    val markerImageView = customMarkerView.findViewById<ImageView>(R.id.imageview)

    val requestOptions = RequestOptions().transform(CircleCrop())
    // Load the image into the ImageView using Glide
    Glide.with(context).asBitmap().load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(requestOptions)
        .into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(
                resource: Bitmap, @Nullable transition:
                Transition<in Bitmap?>?
            ) {
                Log.e("TAG", "onResourceReady: $resource")
                markerImageView.setImageBitmap(resource)
//                callback(customMarkerView)
                callback(createBitmapFromView(customMarkerView))
            }

            override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
        })
}



private fun createBitmapFromView(view: View): Bitmap {
    view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    view.layout(0, 0, view.measuredWidth, view.measuredHeight)
    val bitmap = Bitmap.createBitmap(
        view.measuredWidth, view.measuredHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
    view.draw(canvas)
    return bitmap
}
