package com.rapido.app.ui.home.fragment

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.rapido.app.core.Session
import com.rapido.app.data.pojo.User
import com.rapido.app.ui.activity.IsolatedActivity
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.databinding.HomeFragmentMainBinding
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