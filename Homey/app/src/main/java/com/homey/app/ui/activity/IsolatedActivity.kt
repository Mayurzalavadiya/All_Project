package com.homey.app.ui.activity

import android.os.Bundle
import android.view.View
import com.homey.app.R
import com.homey.app.databinding.IsolatedAcitivtyFullBinding
import com.homey.app.ui.base.BaseActivity
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.manager.ActivityStarter
import com.homey.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IsolatedActivity : BaseActivity() {

    lateinit var isolatedFullActivityBinding: IsolatedAcitivtyFullBinding

    override fun findFragmentPlaceHolder(): Int {
        return R.id.placeHolder
    }

    override fun createViewBinding(): View {
        isolatedFullActivityBinding = IsolatedAcitivtyFullBinding.inflate(layoutInflater)
        return isolatedFullActivityBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar(this,R.color.White,true)
        if (savedInstanceState == null) {

            val page =
                intent.getSerializableExtra(ActivityStarter.ACTIVITY_FIRST_PAGE) as Class<BaseFragment<*>>
            load(page)
                .setBundle(intent.extras!!)
                .replace(false)
        }

    }
}