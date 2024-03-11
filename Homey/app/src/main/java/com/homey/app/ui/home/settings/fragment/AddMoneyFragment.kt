package com.homey.app.ui.home.settings.fragment

import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homey.app.R
import com.homey.app.databinding.FragmentAddMoneyBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.adapter.AddMoneyAdapter
import com.homey.app.ui.home.settings.adapter.LoyaltyHistoryAdapter
import com.homey.app.ui.home.settings.model.AddMoneyData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMoneyFragment : BaseFragment<FragmentAddMoneyBinding>() {

    lateinit var addMoneyAdapter: AddMoneyAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentAddMoneyBinding {
        return FragmentAddMoneyBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setClickListener()
        setUpAdapter()
    }


    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
        buttonAdd.setOnClickListener {
            navigator.goBack()
        }
        textviewAddNew.setOnClickListener { setDialog() }
    }

    private fun setDialog() {
        val addCardBottomSheetFragment = AddCardBottomSheetFragment()
        addCardBottomSheetFragment.show(childFragmentManager, addCardBottomSheetFragment.tag)
        addCardBottomSheetFragment.callBack(::callBack)
    }

    private fun setUpAdapter() = with(binding) {
        addMoneyAdapter = AddMoneyAdapter()
        recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerview.addItemDecoration(ItemDecorator())
        addMoneyAdapter.addItem(getAddMoneyData())
        recyclerview.adapter = addMoneyAdapter
    }

    fun callBack(item: AddMoneyData) {
        addMoneyAdapter.updateItem(item)
    }

    fun getAddMoneyData(): MutableList<AddMoneyData> = mutableListOf<AddMoneyData>().apply {
        add(AddMoneyData("123"))
        add(AddMoneyData("234"))
        add(AddMoneyData("123"))
    }

    inner class ItemDecorator : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = resources.getDimensionPixelSize(R.dimen._10sdp)
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