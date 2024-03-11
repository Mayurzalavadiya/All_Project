package com.example.sample26.activity

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample26.adapter.CategoryAdapter
import com.example.sample26.adapter.RestaurantsAdapter
import com.example.sample26.Const
import com.example.sample26.model.CategoryModal
import com.example.sample26.model.RestaurantsModal
import com.example.sample26.R
import com.example.sample26.databinding.ActivityHomeBinding
import com.example.sample26.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var homeActivityBinding: ActivityHomeBinding

    //category
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryList: ArrayList<CategoryModal>

    //restaurants
    private lateinit var restaurantAdapter: RestaurantsAdapter
    private lateinit var restaurantList: ArrayList<RestaurantsModal>


    override fun createView(): View {
        homeActivityBinding = ActivityHomeBinding.inflate(layoutInflater)
        return homeActivityBinding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onBindActivity() {
        recyclerCategory()
        recyclerRestaurant()
        setClickListener()
        setPopUpMenu()
        setTextColor()
    }

    override fun onResume() {
        super.onResume()
        getArgument()
    }

    private fun getArgument() {
        if (intent.getStringExtra(Const.TOKEN) != null)
            Handler(Looper.getMainLooper()).postDelayed({
                showMessage(intent.getStringExtra(Const.TOKEN)!!)

            }, 500)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerRestaurant() = with(homeActivityBinding) {
        restaurantList = ArrayList()
        val layoutManager =
            LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewRestaurant.layoutManager = layoutManager

        restaurantAdapter = RestaurantsAdapter(restaurantList, this@HomeActivity)
        recyclerViewRestaurant.adapter = restaurantAdapter

        // on below line we are adding data to our list
        restaurantList.add(RestaurantsModal("Pizza", R.drawable.ic_sea_food))
        restaurantList.add(RestaurantsModal("Burger", R.drawable.ic_italian))
        restaurantList.add(RestaurantsModal("sea Food", R.drawable.ic_sea_food))
        restaurantList.add(RestaurantsModal("italian", R.drawable.ic_sea_food))
        restaurantList.add(RestaurantsModal("chines", R.drawable.ic_sea_food))
        restaurantList.add(RestaurantsModal("punjabi", R.drawable.ic_sea_food))

        restaurantAdapter.notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerCategory() = with(homeActivityBinding) {
        categoryList = ArrayList()
        val layoutManager =
            LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.layoutManager = layoutManager

        categoryAdapter = CategoryAdapter(categoryList, this@HomeActivity)
        recyclerViewCategory.adapter = categoryAdapter

        // on below line we are adding data to our list
        categoryList.add(CategoryModal("Pizza", R.drawable.ic_sea_food))
        categoryList.add(CategoryModal("Burger", R.drawable.ic_italian))
        categoryList.add(CategoryModal("sea Food", R.drawable.ic_sea_food))
        categoryList.add(CategoryModal("italian", R.drawable.ic_sea_food))
        categoryList.add(CategoryModal("chines", R.drawable.ic_sea_food))
        categoryList.add(CategoryModal("punjabi", R.drawable.ic_sea_food))

        categoryAdapter.notifyDataSetChanged()

    }


    private fun setPopUpMenu() = with(homeActivityBinding) {
        val popupMenu = PopupMenu(
            this@HomeActivity,
            imageViewProfile
        )

        popupMenu.menu.add(Menu.NONE, 0, 0, R.string.exit)


        popupMenu.setOnMenuItemClickListener { menuItem ->
            val id = menuItem.itemId
            when (id) {
                0 -> {
                    removeAllKey()
                    moveToNextScreen(this@HomeActivity, SplashActivity::class.java)
                    finish()
                }
            }

            false

        }
        imageViewProfile.setOnClickListener {
            popupMenu.show()
        }
    }

    private fun setClickListener() = with(homeActivityBinding) {


        textViewCategories.setOnClickListener {
            moveToNextScreen(this@HomeActivity, CategoryActivity::class.java)
        }
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    showMessage("Home")
                    true
                }

                R.id.heart -> {
                    showMessage("Like")
                    true
                }

                R.id.shopping -> {
                    showMessage("Shopping")
                    true
                }

                R.id.profile -> {
                    showMessage("Profile")
                    true
                }

                else -> {
                    showMessage("none")
                    true
                }
            }
        }
    }


    private fun setTextColor() = with(homeActivityBinding) {
        val ss =
            SpannableString(getString(R.string._30_discount_for_nfast_food))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(this@HomeActivity, R.color.lightGreen)
            }
        }
        ss.setSpan(clickableSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textViewDiscount.text = ss
        textViewDiscount.movementMethod = LinkMovementMethod.getInstance()

    }
}