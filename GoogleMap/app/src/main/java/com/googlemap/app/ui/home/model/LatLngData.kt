package com.googlemap.app.ui.home.model

import com.google.android.gms.maps.model.LatLng


data class LatLngData(
    val location: LatLng,
    val name: String? = null,
    val address: String? = null,
    var isSelect: Boolean = false,
    var image: String? = null
)