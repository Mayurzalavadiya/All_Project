package com.homey.app.ui.home.blogs.fragment

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.homey.app.R
import com.homey.app.databinding.FragmentBlogBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.blogs.adapter.BlogAdapter
import com.homey.app.ui.home.blogs.model.BlogData
import com.homey.app.utils.titleBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BlogFragment : BaseFragment<FragmentBlogBinding>() {

    lateinit var blogAdapter: BlogAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentBlogBinding {
        return FragmentBlogBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        titleBar(requireActivity(), R.color.White, true)
        setUpViewPager()
    }

    private fun setUpViewPager() = with(binding) {

        //View Pager
        blogAdapter = BlogAdapter(requireActivity())
        blogAdapter.addFragment(getItem())
        viewpager.adapter = blogAdapter

        //tabLayout
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = "Tab $position"
            val list = getItem()
            tab.text = list[position].name
        }.attach()
    }




    private fun getItem(): MutableList<BlogData> {
        return mutableListOf<BlogData>().apply {
            add(BlogData(BlogListFragment(), "All"))
            add(BlogData(BlogListFragment(), "Technology"))
            add(BlogData(BlogListFragment(), "Education"))
            add(BlogData(BlogListFragment(), "Lifestyle"))
            add(BlogData(BlogListFragment(), "Sports"))
        }
    }
}