package com.homehaven.app.ui.home.fragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.homehaven.app.core.AppPreferences
import com.homehaven.app.core.Session
import com.homehaven.app.data.pojo.User
import com.homehaven.app.databinding.HomeFragmentMainBinding
import com.homehaven.app.ui.activity.IsolatedActivity
import com.homehaven.app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: BaseFragment<HomeFragmentMainBinding>() {

    @Inject
    lateinit var session: Session

    private var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                activityResult.data?.let {
                    showMessage(it.getStringExtra("my_data")?:"")
                }
            } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                //cancelled
            }
        })

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): HomeFragmentMainBinding {
        return HomeFragmentMainBinding.inflate(inflater,container,attachToRoot)
    }

    override fun bindData() {
        binding.buttonStart.setOnClickListener {
            session.user = User("1")

            navigator.loadActivity(IsolatedActivity::class.java, OrderFragment::class.java)
                .forResult(startForResult)
                .start()
        }
    }
}