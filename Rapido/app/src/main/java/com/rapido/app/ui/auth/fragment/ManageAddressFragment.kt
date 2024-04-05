package com.rapido.app.ui.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapido.app.data.pojo.response.AddressResponse
import com.rapido.app.di.DiConstants
import com.rapido.app.exception.ServerException
import com.rapido.app.ui.auth.adapter.AddressRecyclerAdapter
import com.rapido.app.ui.auth.interfaces.ClickListener
import com.rapido.app.ui.auth.viewmodel.GetAddressViewModel
import com.rapido.app.ui.base.BaseFragment
import com.rapido.app.databinding.FragmentManageAddressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageAddressFragment : BaseFragment<FragmentManageAddressBinding>(), ClickListener {

    private val addressRecyclerAdapter by lazy { AddressRecyclerAdapter(this, getAddressViewModel) }
    private val getAddressViewModel: GetAddressViewModel by viewModels()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentManageAddressBinding {
        return FragmentManageAddressBinding.inflate(inflater, container, attachToRoot)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }


    private fun observeLiveData() {
        getAddressViewModel.getAddressLiveData.observe(this, {
            toggleLoader(false)
            it.data?.let { data ->
                addressRecyclerAdapter.addItem(data)
            }
        }, {
            toggleLoader(false)
            when (it) {
                is ServerException -> {
                    if (it.code == 2) {
                        addressRecyclerAdapter.clear()
                        showMessage(it.message.toString())
                        return@observe false
                    } else {
                        return@observe true
                    }
                }

                else -> {
                    return@observe true
                }
            }
        })

        getAddressViewModel.updateAddressLiveData.observe(this, {
            toggleLoader(false)
            apiCall()
            showMessage(it.message)
        }, {
            toggleLoader(false)
            return@observe true
        })

    }

    override fun bindData() {
        apiCall()
        setUpAdapter()
        setClickListener()
    }

    private fun setUpAdapter() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        adapter = addressRecyclerAdapter
    }

    private fun setClickListener() = with(binding) {
        imageviewBack.setOnClickListener {
            navigator.goBack()
        }
        imageviewAddAddress.setOnClickListener {
            navigator.load(AddAddressFragment::class.java).replace(true)
        }
    }

    private fun apiCall() {
        toggleLoader(true)
        getAddressViewModel.getAddress()
    }

    override fun click(address: AddressResponse, v: View?) {
        navigator.load(AddAddressFragment::class.java).setBundle(
            Bundle().apply {
                putParcelable(DiConstants.ADDRESS, address)
            }).replace(true)
    }
}


/*inner class ItemDecorate : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = resources.getDimensionPixelSize(R.dimen._10dp)
        outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)
        outRect.right = resources.getDimensionPixelSize(R.dimen._10dp)
    }

}*/
