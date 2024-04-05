package com.homey.app.ui.home.settings.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.homey.app.R
import com.homey.app.databinding.BannerAdContainerBinding
import com.homey.app.databinding.ListViewBlogItemBinding
import com.homey.app.ui.home.blogs.fragment.BlogListFragment


class BlogListAdapter(val content: BlogListFragment) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val AD_TYPE = 1
    private val ITEM_TYPE = 2

    val itemList = mutableListOf<Any>()
    lateinit var callBack: (Boolean) -> Unit


    @SuppressLint("NotifyDataSetChanged")
    fun addItem(items: List<Int>) {
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    inner class AdViewHolder(val binding: BannerAdContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Bind ad item data here
        /* init {
             // Initialize Mobile Ads and set ad unit ID only once
             MobileAds.initialize(content.requireActivity()) {}
         }*/
        fun bind(position: Int) = with(binding) {

            val adView = itemList[position] as AdView


            adCardView.addView(adView)

            Log.e("TAG", "bind: sdsdsds", )
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
            adView.adListener = object : AdListener() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    val toastMessage: String = "ad fail to load"
                    content.showMessage(toastMessage)
                    Log.e("TAG", "onAdFailedToLoad: $toastMessage", )
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    val toastMessage: String = "ad loaded"
                    content.showMessage(toastMessage)
                    Log.e("TAG", "onAdFailedToLoad: $toastMessage", )
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                    val toastMessage: String = "ad is open"
                    content.showMessage(toastMessage)
                    Log.e("TAG", "onAdFailedToLoad: $toastMessage", )
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                    val toastMessage: String = "ad is clicked"
                    content.showMessage(toastMessage)
                    Log.e("TAG", "onAdFailedToLoad: $toastMessage", )
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                    val toastMessage: String = "ad is closed"
                    content.showMessage(toastMessage)
                    Log.e("TAG", "onAdFailedToLoad: $toastMessage", )
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    val toastMessage: String = "ad impression"
                    content.showMessage(toastMessage)
                    Log.e("TAG", "onAdFailedToLoad: $toastMessage", )
                }

            }
        }
    }


    inner class ViewHolder(val binding: ListViewBlogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {

            textviewDescription.setOnClickListener {
                callBack(!textviewDescription.expanded)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AD_TYPE -> {
                AdViewHolder(
                    BannerAdContainerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                ViewHolder(
                    ListViewBlogItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            AD_TYPE -> {
                (holder as AdViewHolder).bind(position)
            }

            ITEM_TYPE -> {
                (holder as ViewHolder).bind(position)
            }
        }
    }


    fun onClick(kFunction1: (Boolean) -> Unit) {
        this.callBack = kFunction1
    }

    override fun getItemViewType(position: Int): Int {
        // Return view type based on position
        return if (position == BlogListFragment.ITEMS_PER_AD) {
            AD_TYPE // For example, show ad every 5th position
        } else {
            ITEM_TYPE
        } // For example, show ad every 5th position
    }
}