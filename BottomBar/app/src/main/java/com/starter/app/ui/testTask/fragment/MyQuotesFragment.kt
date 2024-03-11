package com.starter.app.ui.testTask.fragment

import android.app.Activity
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.starter.app.R
import com.starter.app.databinding.FragmentMyQuotesBinding
import com.starter.app.ui.Const
import com.starter.app.ui.activity.IsolatedActivity
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.testTask.adapter.MyPostAdapter
import com.starter.app.ui.testTask.interfaces.EditListener
import com.starter.app.ui.testTask.pojo.MyQuotes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyQuotesFragment : BaseFragment<FragmentMyQuotesBinding>(), EditListener {

    companion object {
        val data = mutableListOf<MyQuotes>()
    }

    private val myPostAdapter by lazy { MyPostAdapter(this) }


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentMyQuotesBinding {
        return FragmentMyQuotesBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        setClickListener()
        setAdapter()
    }

    override fun onResume() {
        super.onResume()
       addData()
    }

    private var startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                activityResult.data?.let {
                    val dataList = it.getParcelableExtra(Const.DATA) as MyQuotes?
                    if (dataList != null) {
                        data.add(dataList)
                        addData()
//                        myPostAdapter.addItem(dataList)
                    }
                }
            } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                //cancelled
            }
        })

    private fun setClickListener() = with(binding) {
        imageViewAdd.setOnClickListener {
            navigator.loadActivity(IsolatedActivity::class.java, AddFragment::class.java)
                .forResult(startForResult)
                .start()
        }
    }

    private fun setAdapter() = with(binding) {
        recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = myPostAdapter
            addItemDecoration(ItemDecoratorVertical())
        }
    }

    private fun addData() {
        myPostAdapter.clear()
        myPostAdapter.addItems(data)
    }

    override fun onClick(position: Int, boolean: Boolean, v: View?) {
        if (boolean) {
            data.removeAt(position)
            addData()
        } else {
            editData(position)
        }
    }

    private fun editData(position: Int) {
        val bundle = Bundle().apply {
            putString(Const.POSITION, position.toString())
        }
        navigator.loadActivity(IsolatedActivity::class.java, AddFragment::class.java)
            .addBundle(bundle)
            .start()
    }

    inner class ItemDecoratorVertical : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = resources.getDimensionPixelSize(R.dimen._3sdp)
        }

        private val divider =
            ContextCompat.getDrawable(requireActivity(), R.drawable.recycler_divider)

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)

            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childSize = parent.childCount

            for (i in 0 until childSize) {
                val child = parent.getChildAt(i)

                val layoutParams = (child.layoutParams as RecyclerView.LayoutParams)

                val top = child.bottom + layoutParams.bottomMargin
                val bottom = top + (divider?.intrinsicHeight ?: 0)

                divider?.setBounds(left, top, right, bottom)
                divider?.draw(c)
            }
        }
    }


}