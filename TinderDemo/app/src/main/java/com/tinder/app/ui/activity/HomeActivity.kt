package com.tinder.app.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.tinder.app.R
import com.tinder.app.databinding.HomeActivityBinding
import com.tinder.app.ui.activity.adapter.arrayAdapter
import com.tinder.app.ui.activity.model.UsersData
import com.tinder.app.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private var arrayAdapter: arrayAdapter? = null
    private val rowItems = mutableListOf<UsersData>()
    lateinit var binding: HomeActivityBinding

    override fun createViewBinding(): View {
        binding = HomeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun findFragmentPlaceHolder(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpAdapter()
    }

    private fun setClickListener() = with(binding) {
    }


    private fun setUpAdapter() = with(binding) {
        rowItems.apply {
            add(UsersData("Mayur", R.drawable.hlis_image))
            add(UsersData("Mayur", R.drawable.hlis_image))
            add(UsersData("Mayur", R.drawable.hlis_image))
            add(UsersData("Mayur", R.drawable.hlis_image))
            add(UsersData("Mayur", R.drawable.hlis_image))
            add(UsersData("Mayur", R.drawable.hlis_image))
        }
        Log.e("TAG", "setUpAdapter: $rowItems")
        rowItems.let { arrayAdapter = arrayAdapter(this@HomeActivity, R.layout.item_view, it) }
        val flingContainer = swipe as SwipeFlingAdapterView
        flingContainer.setAdapter(arrayAdapter)
        flingContainer.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                rowItems.removeAt(0)
                arrayAdapter!!.notifyDataSetChanged()
            }

            override fun onLeftCardExit(o: Any) {
                showMessage("Nope")
            }

            override fun onRightCardExit(o: Any) {
                showMessage("Like")
            }

            override fun onAdapterAboutToEmpty(i: Int) {}
            override fun onScroll(v: Float) {}
        })
        flingContainer.setOnItemClickListener { i, o ->
            Toast.makeText(
                this@HomeActivity,
                "data is " + readln()[i],
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setData() {

    }
}