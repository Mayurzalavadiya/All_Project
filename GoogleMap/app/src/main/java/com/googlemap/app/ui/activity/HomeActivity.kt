package com.googlemap.app.ui.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.googlemap.app.R
import com.googlemap.app.databinding.HomeActivityBinding
import com.googlemap.app.ui.base.BaseActivity
import com.googlemap.app.ui.home.adapter.LocationAdapter
import com.googlemap.app.ui.home.model.LatLngData
import com.googlemap.app.utils.BitmapHelper
import com.googlemap.app.utils.getMarkerBitmapFromView
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.Locale


@AndroidEntryPoint
class HomeActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener,
    GoogleMap.OnMarkerClickListener {

    private lateinit var locationAdapter: LocationAdapter
    private lateinit var binding: HomeActivityBinding

    private lateinit var mMap: GoogleMap

    private var polyLine: Polyline? = null
    private var circle: Circle? = null
    private var polygon: Polygon? = null

    private val itemList = mutableListOf<LatLng>()

    private val markerList = mutableListOf<Marker>()
    private val circleList = mutableListOf<Circle>()
    private val polygonList = mutableListOf<Polygon>()

    private var isCircle: Boolean = false
    private var isRectangle: Boolean = false
    private var isLine: Boolean = false
    private var isLink: Boolean = false
    private var isCar: Boolean = false

    private var isZoom: Boolean = false

    private val list: MutableList<LatLngData> = getLatLng()

    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAdapter()
        setMap()
        setClickListener()
    }

    private fun isFalse() {
        isCircle = false
        isRectangle = false
        isLine = false
        isLink = false
        isCar = false
    }

    private fun setClickListener() = with(binding) {
        imageviewDelete.setOnClickListener {
            locationAdapter.clearList()
            isFalse()
            clearMarkers()
            clearPolyline()
            clearCircle()
            clearPolygon()
            setColor()
            itemList.clear()
            imageviewCar.isClickable = true
        }

        imageviewCircle.setOnClickListener {
            isRectangle = false
            isLine = false
            isLink = false
            isCar = false
            isCircle = !isCircle
            clearPolygon()
            setColor()
        }
        imageviewRectangle.setOnClickListener {
            isCircle = false
            isLine = false
            isCar = false
            isLink = false
            isRectangle = !isRectangle
            clearPolygon()
            setColor()
        }

        imageviewLine.setOnClickListener {
            isCircle = false
            isRectangle = false
            isCar = false
            isLink = false
            clearPolygon()
            if (markerList.isNotEmpty()) {
                isLine = !isLine
            } else {
                showMessage("Please add location")
            }

            setColor()
        }
        imageviewLink.setOnClickListener {
            isCircle = false
            isRectangle = false
            isLine = false
            isCar = false
            setColor()
            isLink = !isLink
            if (markerList.isNotEmpty()) {
                if (isLink) {
                    val markerPositions = markerList.map { it.position }
                    addPolygon(markerPositions)
                } else {
                    clearPolygon()
                }
                setColor()
            } else {
                showMessage("Please add location")
            }
        }

        slider.addOnChangeListener { _, value, _ ->
            updateCircleRadius(value)
        }

        imageviewPlus.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomIn())
            isZoom = true
        }

        imageviewMinus.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.zoomOut())
        }

        imageviewLayers.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        }
        imageviewCar.setOnClickListener {
            if (markerList.isNotEmpty()) {
                moveVehicle()
                isZoom = false
                imageviewCar.isClickable = false
            } else {
                showMessage("Please add location")
            }
        }

        imageviewFocus.setOnClickListener {
            isZoom = false
        }
    }


    private fun updateCircleRadius(progress: Float) {
        // Ensure that a circle is already created
        if (circle != null) {
            // Update the circle radius based on the progress
            val radius = progress.toDouble() * 100.0 // Adjust the multiplier as needed
            circle?.radius = radius
        }
    }

    private fun setColor() = with(binding) {
        binding.slider.visibility = View.GONE

        imageviewCircle.setBackgroundColor(Color.TRANSPARENT)
        imageviewRectangle.setBackgroundColor(Color.TRANSPARENT)
        imageviewLine.setBackgroundColor(Color.TRANSPARENT)
        imageviewLink.setBackgroundColor(Color.TRANSPARENT)
        imageviewCircle.setColorFilter(
            Color.BLACK
        )
        imageviewRectangle.setColorFilter(
            Color.BLACK
        )
        imageviewLine.setColorFilter(
            Color.BLACK
        )
        imageviewLink.setColorFilter(
            Color.BLACK
        )

        when {
            isCircle -> {
                imageviewCircle.setColorFilter(
                    Color.WHITE
                )
                imageviewCircle.setBackgroundColor(Color.DKGRAY)
            }

            isRectangle -> {
                imageviewRectangle.setColorFilter(
                    Color.WHITE
                )
                imageviewRectangle.setBackgroundColor(Color.DKGRAY)
            }

            isLine -> {
                imageviewLine.setColorFilter(
                    Color.WHITE
                )
                imageviewLine.setBackgroundColor(Color.DKGRAY)
            }

            isLink -> {
                imageviewLink.setColorFilter(
                    Color.WHITE
                )
                imageviewLink.setBackgroundColor(Color.DKGRAY)
            }
        }
    }

    private fun setAdapter() = with(binding.recyclerview) {
        locationAdapter = LocationAdapter()
        layoutManager = LinearLayoutManager(
            this@HomeActivity, LinearLayoutManager.VERTICAL, false
        )
        adapter = locationAdapter
        locationAdapter.callBack(::callBack)
    }


    private fun setMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener(this)
        mMap.setOnMarkerClickListener(this)


        addMarkers(list)
        val location = getLatLng()
        moveCamera(location[0].location, 10f)

    }

    private fun addMarkers(latLng: MutableList<LatLngData>) {
        for (location in latLng) {

            location.image?.let {
                getMarkerBitmapFromView(this, it) { bitmap ->
                    // Once the bitmap is ready, add the marker to the map
                    Log.e("TAG", "onMapClick: $bitmap")

//                    val mIconGenerator = IconGenerator(applicationContext)
//                    mIconGenerator.setContentView(bitmap)
//                    val iconBitmap = mIconGenerator.makeIcon()
                    val marker = mMap.addMarker(
                        MarkerOptions().position(location.location)
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    )

                    marker?.let { mar ->
                        markerList.add(mar)
                    }

                }
            }
        }
    }


    private fun moveCamera(latLngData: LatLng, zoom: Float, rotation: Float = 0f) {

        val cameraPosition = CameraPosition.Builder()
            .target(latLngData)
            .zoom(zoom)
            .bearing(rotation)
            .build()

//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngData, zoom))

    }

    private fun callBack(item: LatLng) {
        itemList.add(item)
        addPolyline(mMap, itemList)
    }

    private fun addPolyline(googleMap: GoogleMap, latLng: List<LatLng>) {
        clearPolyline()

        val pattern = listOf(Dot(), Gap(20F), Dash(30F), Gap(20F))
        val polylineOptions = PolylineOptions().apply {
//            pattern(pattern)
            width(5f)
            color(Color.BLUE)
            geodesic(true)
        }

        if (latLng.size > 1) {
            // Add each place's LatLng coordinate to the polyline
            latLng.forEach { place ->
                polylineOptions.add(place)
            }
        }
        // Add the polyline to the map
        polyLine = googleMap.addPolyline(polylineOptions)
    }

// [END maps_android_add_map_codelab_ktx_add_markers]

    private fun clearPolyline() {
        polyLine?.remove() // Remove the polyline from the map
        polyLine = null // Set the reference to null
    }

    private fun getLatLng(): MutableList<LatLngData> {
        return mutableListOf<LatLngData>().apply {
            add(LatLngData(LatLng(23.084591, 72.588501), image = getItem()))
            add(LatLngData(LatLng(23.030357, 72.517845), image = getItem()))
            add(
                LatLngData(
                    LatLng(23.070157, 72.627560),
                    image = getItem()
                )
            )
            add(LatLngData(LatLng(22.993064, 72.381577)))
            add(
                LatLngData(
                    LatLng(23.222949, 72.638554),
                    image = getItem()
                )
            )

        }
    }


    override fun onMapClick(latLng: LatLng) {

        getAddress(latLng)
        when {
            isCircle -> {
                addCircle(latLng)
            }

            isRectangle -> {

                addPolygon(createPolygon(latLng))
            }

            else -> {
                // Assuming this code is inside an activity or fragment
                val imageUrl = getItem()
                list.add(LatLngData(latLng, image = imageUrl))
                getMarkerBitmapFromView(this, imageUrl) { bitmap ->
                    // Once the bitmap is ready, add the marker to the map
                    Log.e("TAG", "onMapClick: $bitmap")

//                    val mIconGenerator = IconGenerator(applicationContext)
//                    mIconGenerator.setContentView(bitmap)
//                    val iconBitmap = mIconGenerator.makeIcon()
                    val marker = mMap.addMarker(
                        MarkerOptions().position(latLng)
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                    )

                    marker?.let {
                        markerList.add(it)
                    }
                }

            }
        }

        if (isLink) {
            val markerPositions = markerList.map { it.position }
            clearPolygon()
            addPolygon(markerPositions)
        }
//        locationAdapter.updateItem(LatLngData(latLng))
    }


    override fun onMarkerClick(marker: Marker): Boolean {

        when {
            isLine -> {
                if (itemList.contains(marker.position)) {

                    if (marker.position != itemList.last()) {
                        Log.e("TAG", "onMarkerClick: {$itemList")
                        Log.e(
                            "TAG",
                            "onMarkerClick: ${itemList.size}${itemList[1]} ${marker.position}"
                        )

                        if (marker.position == itemList[itemList.lastIndex - 1]) {
                            itemList.removeAt(itemList.lastIndex)
                        } else {

                            itemList.add(marker.position)
                        }
                    }


                } else {
                    itemList.add(marker.position)
                }

                Log.e("TAG", "onMarkerClick: ${itemList.size}")

                addPolyline(mMap, itemList)
            }

            isCircle -> {
                addCircle(marker.position)
            }

            else -> {

                list.forEach {
                    if (it.location == marker.position) {
                        Log.e("TAG", "onMarkerClick: ${it.location}")
                        locationAdapter.updateItem(it)
                    }
                }
            }
        }
        return true
    }


    private fun clearMarkers() {
        for (marker in markerList) {
            marker.remove()
        }
        markerList.clear()
    }


    // [START maps_android_add_map_codelab_ktx_add_circle]
    /*
     * Adds a [Circle] around the provided [item]
     */
    private fun addCircle(latLng: LatLng) {
        locationAdapter.clearList()
        binding.slider.visibility = View.VISIBLE
        moveCamera(latLng, 12f)
        circle = mMap.addCircle(
            CircleOptions().center(latLng).radius(1000.0).strokeWidth(3f)
                .strokeColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .fillColor(ContextCompat.getColor(this, R.color.colorPrimaryTranslucent))
                .clickable(true)
        )
        circleList.add(circle!!)
        binding.slider.value = 10f

    }

    private fun clearCircle() {
        for (circle in circleList) {
            circle.remove() // Remove each circle from the map
        }
        circleList.clear() // Clear the list of circles
    }

    private fun addPolygon(latLng: List<LatLng>) {
        val polygonOptions = PolygonOptions().addAll(latLng).strokeWidth(5f)
            .strokeColor(ContextCompat.getColor(this, R.color.colorAccent))
            .fillColor(ContextCompat.getColor(this, R.color.purple_200))
        polygon = mMap.addPolygon(polygonOptions)
        polygonList.add(polygon!!)
    }

    private fun clearPolygon() {
        for (polygon in polygonList) {
            polygon.remove()
        }
        polygonList.clear()
    }

    private fun createPolygon(latLng: LatLng): List<LatLng> {
        return listOf(
            LatLng(latLng.latitude, latLng.longitude),
            LatLng(latLng.latitude + .1, latLng.longitude),
            LatLng(latLng.latitude + .1, latLng.longitude + .1),
            LatLng(latLng.latitude, latLng.longitude + .1)
        )
    }


    private fun getItem(): String {
        return arrayOf(
            "https://images.unsplash.com/photo-1709422995958-5d6effd595b6?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHx0b3BpYy1mZWVkfDh8NnNNVmpUTFNrZVF8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1709633694594-30f42ddbf669?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw5fHx8ZW58MHx8fHx8",
            "https://images.unsplash.com/photo-1709065396771-72494e569cb0?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHx0b3BpYy1mZWVkfDMxfE04alZiTGJUUndzfHxlbnwwfHx8fHw%3D",
            "https://images.unsplash.com/photo-1550785188-cb206bec4318?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHx0b3BpYy1mZWVkfDQ5fDZzTVZqVExTa2VRfHxlbnwwfHx8fHw%3D",
            "https://images.unsplash.com/photo-1462733248196-cafb2aa9a9f9?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHx0b3BpYy1mZWVkfDN8NnNNVmpUTFNrZVF8fGVufDB8fHx8fA%3D%3D",
        ).random()
    }

    private fun moveVehicle() {
        moveMarkerRecursively(0)
        val markerPositions = markerList.map { it.position }
        addPolyline(mMap, markerPositions)
    }

    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.colorPrimary)
        BitmapHelper.vectorToBitmap(this, R.drawable.car_icon, color)
    }

    private fun moveMarkerRecursively(index: Int) {
        if (index >= markerList.size - 1) {
            // Movement completed
            binding.imageviewCar.isClickable = true
            return
        }

        val startPosition = markerList[index].position
        val finalPosition = markerList[index + 1].position

        val handler = android.os.Handler(mainLooper)
        val start = SystemClock.uptimeMillis()
        val durationInMs = 5000f
        val interpolator: Interpolator = AccelerateDecelerateInterpolator()


        val carMarker = mMap.addMarker(
            MarkerOptions().position(startPosition).icon(bicycleIcon)
        )

        handler.post(object : Runnable {
            override fun run() {
                val elapsed = SystemClock.uptimeMillis() - start
                val t = elapsed / durationInMs
                interpolator.getInterpolation(t)

                val currentPosition = LatLng(
                    startPosition.latitude * (1 - t) + finalPosition.latitude * t,
                    startPosition.longitude * (1 - t) + finalPosition.longitude * t
                )

                val rotation = getBearing(currentPosition, startPosition, finalPosition)
                carMarker?.position = currentPosition
                carMarker?.rotation = rotation // Update car rotation


                if (t < 1) {
                    handler.postDelayed(this, 16)
                } else {
                    // Movement completed, move to the next marker
                    carMarker?.remove() // Remove the current marker
                    moveMarkerRecursively(index + 1)
                }
            }
        })
    }

    private fun getBearing(
        currentPosition: LatLng,
        startPosition: LatLng,
        finalPosition: LatLng
    ): Float {
        val startLocation = Location("Start")
        startLocation.latitude = startPosition.latitude
        startLocation.longitude = startPosition.longitude

        val finalLocation = Location("Final")
        finalLocation.latitude = finalPosition.latitude
        finalLocation.longitude = finalPosition.longitude


        var bearing = startLocation.bearingTo(finalLocation)
//        moveCamera(currentPosition, 10f, bearing)
        // Adjust the bearing based on the map's current bearing
        bearing -= mMap.cameraPosition.bearing

        return bearing
    }

    private fun getAddress(location: LatLng) {
        val geocoder = Geocoder(this@HomeActivity, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            addresses?.let {
                Log.e("TAG", "getAddress: ${it[0].subLocality}")
                val sAddress = it[0].getAddressLine(0)
                val sCity = it[0].locality
                val sSubCity = it[0].subLocality
                val sState = it[0].adminArea
                val sCountryCode = it[0].countryCode
                val sCountry = it[0].countryName
                val sPostCode = it[0].postalCode
                val sKnownName = it[0].featureName
                Log.e(
                    "TAG",
                    "getAddress: Address : $sAddress\nCity : $sCity\nSub City : $sSubCity\nState : $sState\nCountry : $sCountry ($sCountryCode)\nPostCode : $sPostCode\nKnown Name : $sKnownName"
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("RESPONSE", e.message!!)
        }
    }
}


