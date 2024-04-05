package com.example.test.ui.fragment

import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.pojo.MyQuotes
import com.example.test.databinding.FragmentMyQuotesBinding
import com.example.test.ui.adapter.MyPostAdapter
import com.example.test.ui.base.BaseFragment
import com.example.test.ui.base.Const
import com.example.test.ui.interfaces.EditListener


class MyQuotesFragment : BaseFragment<FragmentMyQuotesBinding>(), EditListener {

    companion object {
        val data = mutableListOf<MyQuotes>()
    }

    private val myPostAdapter by lazy { MyPostAdapter(this) }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentMyQuotesBinding {
        return FragmentMyQuotesBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() {
        setClickListener()
        setAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().supportFragmentManager
            .setFragmentResultListener(Const.DATA, requireActivity()) { _, bundle ->
                val image = bundle.getInt(Const.IMAGE)
                val title = bundle.getString(Const.TITLE)
                val des = bundle.getString(Const.DESCRIPTION)
                val location = bundle.getString(Const.LOCATION)
                data.add(MyQuotes(image, title, des, location))
            }
    }


    private fun setClickListener() = with(binding) {
        imageViewAdd.setOnClickListener {
            loadFragment(AddFragment())
        }
    }

    private fun setAdapter() = with(binding.recyclerView) {
        layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        adapter = myPostAdapter
        addItemDecoration(ItemDecoratorVertical())
        addData()

    }

    private fun addData() {
        myPostAdapter.clear()
        myPostAdapter.addItem(data)
    }

    inner class ItemDecoratorVertical : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.top = resources.getDimensionPixelSize(R.dimen._3dp)
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


    private fun editData(position: Int) {
        val addFragment = AddFragment()
        addFragment.arguments = Bundle().apply {
            putInt(Const.POSITION, position)
        }
        loadFragment(addFragment)
    }

    override fun onClick(position: Int, boolean: Boolean, v: View?) {
        if (boolean) {
            data.removeAt(position)
            addData()
        } else {
            editData(position)
        }
    }

}