package com.starter.app.ui.productTask.fragment

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.starter.app.R
import com.starter.app.databinding.FragmentProductListBinding
import com.starter.app.ui.Const
import com.starter.app.ui.activity.IsolatedActivity
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.productTask.adapter.MyProductAdapter
import com.starter.app.ui.productTask.interfaces.ClickListener
import com.starter.app.ui.productTask.pojo.MyProduct
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding>(), ClickListener {

    private val myPostAdapter by lazy { MyProductAdapter(this) }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentProductListBinding {
        return FragmentProductListBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {

        setAdapter()
        setClickListener()
    }


    private fun setAdapter() = with(binding) {
        recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            recyclerView.addItemDecoration(ItemDecorate())
            adapter = myPostAdapter
        }
    }

    private var startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                activityResult.data?.let { it ->
                    val dataList = it.getParcelableExtra(Const.DATA) as MyProduct?
                    dataList?.let { item ->
                        Log.e("TAG", ":$item ", )
                        if (item.position == null) {
                            myPostAdapter.addItem(dataList)
                        } else{
                            myPostAdapter.updateItem(dataList)
                        }
                    }
                }
            } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
                //cancelled
            }
        })

    private fun setClickListener() = with(binding) {
        imageViewAdd.setOnClickListener {
            navigator.loadActivity(IsolatedActivity::class.java, AddProductFragment::class.java)
                .forResult(startForResult)
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

            outRect.top = resources.getDimensionPixelSize(R.dimen._10sdp)
        }

    }

    override fun onClick(position: Int, v: View?) {
        val bundle = Bundle().apply {
            putString(Const.POSITION, position.toString())
            putParcelable(Const.PRODUCT, myPostAdapter.getItemList(position))
        }
        navigator.loadActivity(IsolatedActivity::class.java, AddProductFragment::class.java)
            .addBundle(bundle).forResult(startForResult)
            .start()
    }

}