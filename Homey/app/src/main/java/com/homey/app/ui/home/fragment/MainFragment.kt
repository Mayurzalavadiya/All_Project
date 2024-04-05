package com.homey.app.ui.home.fragment

import android.app.Activity
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.core.AppPreferences
import com.homey.app.core.Session
import com.homey.app.data.pojo.User
import com.homey.app.databinding.HomeFragmentMainBinding
import com.homey.app.ui.activity.IsolatedActivity
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.favorite.adapter.FavoriteAdapter
import com.homey.app.utils.StaticData
import com.homey.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<HomeFragmentMainBinding>() {

    lateinit var favoriteAdapter: FavoriteAdapter

    @Inject
    lateinit var appPreferences: AppPreferences
    private var startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { activityResult ->
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    activityResult.data?.let {
                        showMessage(it.getStringExtra("my_data") ?: "")
                    }
                } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                    //cancelled
                }
            })

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): HomeFragmentMainBinding {
        return HomeFragmentMainBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        titleBar(requireActivity(), R.color.colorPrimary)

        setUpAdapter()
        /*
        binding.buttonStart.setOnClickListener {
            session.user = User("1")

            navigator.loadActivity(IsolatedActivity::class.java, OrderFragment::class.java)
                .forResult(startForResult)
                .start()
        }*/
    }


    private fun setUpAdapter() = with(binding) {
        favoriteAdapter = FavoriteAdapter()
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        favoriteAdapter.addItem(StaticData().getFavoriteList())
        recyclerview.addItemDecoration(ItemDecorate())
        recyclerview.adapter = favoriteAdapter
    }



    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = resources.getDimensionPixelSize(R.dimen._15sdp)
        }

    }
}