package com.googlemap.app.ui.home.fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.googlemap.app.R
import com.googlemap.app.databinding.HomeFragmentMainBinding
import com.googlemap.app.ui.base.BaseFragment
import com.googlemap.app.ui.home.adapter.LocationAdapter
import com.googlemap.app.ui.home.model.LatLngData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<HomeFragmentMainBinding>() {


    lateinit var locationAdapter: LocationAdapter
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): HomeFragmentMainBinding {
        return HomeFragmentMainBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
    }


}