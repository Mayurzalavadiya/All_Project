package com.bankpick.app.ui.activity

import android.os.Bundle
import android.view.View
import com.bankpick.app.R
import com.bankpick.app.databinding.IsolatedAcitivtyFullBinding
import com.bankpick.app.ui.base.BaseActivity
import com.bankpick.app.ui.base.BaseFragment
import com.bankpick.app.ui.manager.ActivityStarter
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