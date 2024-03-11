package com.homey.app.ui.home.blogs.fragment

import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.FragmentBlogListBinding
import com.homey.app.ui.activity.IsolatedActivity
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.adapter.BlogListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogListFragment : BaseFragment<FragmentBlogListBinding>() {


    lateinit var blogListAdapter: BlogListAdapter
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentBlogListBinding {
        return FragmentBlogListBinding.inflate(
            inflater, container, attachToRoot
        )
    }

    override fun bindData() {
        setUpAdapter()
    }

    private fun setUpAdapter() = with(binding) {
        blogListAdapter = BlogListAdapter()
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        blogListAdapter.addItem(listOf(1, 2, 3, 4, 5, 6))
        recyclerview.addItemDecoration(ItemDecorate())
        recyclerview.adapter = blogListAdapter
        blogListAdapter.onClick(::callBack)
    }

    fun callBack(isclick: Boolean) {
        Log.e("TAG", "callBack: $isclick")
        if (isclick) {
            navigator.loadActivity(IsolatedActivity::class.java, BlogDetailsFragment::class.java)
                .start()
        }
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = resources.getDimensionPixelSize(R.dimen._11sdp)
        }

    }
}