package com.homey.app.ui.home.blogs.fragment

import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.homey.app.R
import com.homey.app.databinding.FragmentBlogListBinding
import com.homey.app.ui.activity.IsolatedActivity
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.adapter.BlogListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogListFragment : BaseFragment<FragmentBlogListBinding>() {


    private lateinit var  blogListAdapter: BlogListAdapter
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

    private fun addBannerAds() {
        // Loop through the items array and place a new banner ad in every ith position in
        // the items List.
        val i = ITEMS_PER_AD
            val adView = AdView(requireActivity())
            adView.setAdSize(AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(requireActivity(), adWidth))
            adView.adUnitId = AD_UNIT_ID
            blogListAdapter.itemList.add(i, adView)
        }

    private fun loadBannerAds() {
        // Load the first banner ad in the items list (subsequent ads will be loaded automatically
        // in sequence).
        loadBannerAd(ITEMS_PER_AD)
    }

    private fun loadBannerAd(index: Int) {
        if (index >= blogListAdapter.itemList.size) {
            return
        }
        val item =
            blogListAdapter.itemList[index] as? AdView
                ?: throw ClassCastException("Expected item at index $index to be a banner ad ad.")

        // Set an AdListener on the AdView to wait for the previous banner ad
        // to finish loading before loading the next ad in the items list.
        item.adListener =
            object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    // The previous banner ad loaded successfully, call this method again to
                    // load the next ad in the items list.
//                    loadBannerAd(index + ITEMS_PER_AD)
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    // The previous banner ad failed to load. Call this method again to load
                    // the next ad in the items list.
                    val error =
                        String.format(
                            "domain: %s, code: %d, message: %s",
                            loadAdError.domain,
                            loadAdError.code,
                            loadAdError.message,
                        )
                    Log.e(
                        "MainActivity",
                        "The previous banner ad failed to load with error: " +
                                error +
                                ". Attempting to" +
                                " load the next banner ad in the items list.",
                    )
//                    loadBannerAd(index + ITEMS_PER_AD)
                }
            }

        // Load the banner ad.
        item.loadAd(AdRequest.Builder().build())
    }
    companion object {
        // A banner ad is placed in every 8th position in the RecyclerView.
        const val ITEMS_PER_AD = 1
        private const val AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"
    }

    private val adWidth: Int
        get() {
            val displayMetrics = resources.displayMetrics
            val adWidthPixels =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val windowMetrics: WindowMetrics = requireActivity().windowManager.currentWindowMetrics
                    windowMetrics.bounds.width()
                } else {
                    displayMetrics.widthPixels
                }
            val density = displayMetrics.density
            return (adWidthPixels / density).toInt()
        }
    private fun setUpAdapter() = with(binding) {
        blogListAdapter = BlogListAdapter(this@BlogListFragment)
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        blogListAdapter.addItem(listOf(1, 2, 3, 4, 5))
        recyclerview.addItemDecoration(ItemDecorate())
        addBannerAds()
        loadBannerAds()
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