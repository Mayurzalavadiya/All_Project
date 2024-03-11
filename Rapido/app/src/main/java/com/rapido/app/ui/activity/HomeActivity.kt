package com.rapido.app.ui.activity

import android.os.Bundle
import android.view.View
import com.rapido.app.R
import com.rapido.app.databinding.HomeActivityBinding
import com.rapido.app.ui.auth.fragment.ManageAddressFragment
import com.rapido.app.ui.base.BaseActivity
import com.rapido.app.ui.home.fragment.MainFragment
import com.rapido.app.ui.home.fragment.OrderFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    lateinit var binding: HomeActivityBinding

    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load(ManageAddressFragment::class.java).replace(false)
    }
}
