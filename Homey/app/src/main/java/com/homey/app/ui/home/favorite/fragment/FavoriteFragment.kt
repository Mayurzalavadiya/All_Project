package com.homey.app.ui.home.favorite.fragment

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.FragmentFavoriteBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.favorite.adapter.FavoriteAdapter
import com.homey.app.ui.home.favorite.model.FavoriteData
import com.homey.app.utils.StaticData
import com.homey.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {


    lateinit var favoriteAdapter: FavoriteAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        titleBar(requireActivity(), R.color.White, true)
        setUpAdapter()
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