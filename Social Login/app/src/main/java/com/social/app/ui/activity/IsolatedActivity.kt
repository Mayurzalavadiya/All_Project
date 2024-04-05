package com.social.app.ui.activity

import android.os.Bundle
import android.view.View
import com.social.app.R
import com.social.app.databinding.IsolatedAcitivtyFullBinding
import com.social.app.ui.base.BaseActivity
import com.social.app.ui.base.BaseFragment
import com.social.app.ui.manager.ActivityStarter
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

        if (savedInstanceState == null) {

            val page =
                intent.getSerializableExtra(ActivityStarter.ACTIVITY_FIRST_PAGE) as Class<BaseFragment<*>>
            load(page)
                .setBundle(intent.extras!!)
                .replace(false)
        }

    }
}