package com.rangeseekbar.app.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jaygoo.demo.fragments.RangeSeekBarFragment
import com.jaygoo.demo.fragments.SingleSeekBarFragment
import com.jaygoo.demo.fragments.StepsSeekBarFragment
import com.jaygoo.demo.fragments.VerticalSeekBarFragment
import com.rangeseekbar.app.R
import com.rangeseekbar.app.databinding.HomeActivityBinding
import com.rangeseekbar.app.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    val types = arrayOf("SINGLE", "RANGE", "STEP", "VERTICAL")

    var fragments: MutableList<Fragment> = ArrayList()

    lateinit var binding: HomeActivityBinding


    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = R.id.placeHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        fragments.clear()
        fragments.add(SingleSeekBarFragment())
        fragments.add(RangeSeekBarFragment())
        fragments.add(StepsSeekBarFragment())
        fragments.add(VerticalSeekBarFragment())
       setViewPager()
        for (s in types) {
            binding.layouttab.newTab().setText(s)
        }
    }

    private fun setViewPager() = with(binding) {
        viewpager.adapter = PagerAdapter(types,fragments,supportFragmentManager)
        layouttab.setupWithViewPager(viewpager)
    }


    private class PagerAdapter(
        val name: Array<String>,
        val fragments: MutableList<Fragment>,
        fm: FragmentManager
    ) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }


        override fun getCount(): Int = fragments.size

        override fun getPageTitle(position: Int): CharSequence {
            return name[position]
        }
    }

}

