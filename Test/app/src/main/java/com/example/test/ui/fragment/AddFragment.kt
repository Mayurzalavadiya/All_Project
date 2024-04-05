package com.example.test.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.databinding.FragmentAddBinding
import com.example.test.ui.adapter.GridAdapter
import com.example.test.ui.base.BaseFragment
import com.example.test.ui.base.Const
import com.example.test.ui.interfaces.ClickListener

class AddFragment : BaseFragment<FragmentAddBinding>(), ClickListener {

    private var isImageSelect = false
    private var id: Int? = null
    private var imageId: Int? = null
    private val imageList = mutableListOf<Int>()
    private val adapter by lazy {
        GridAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            id = requireArguments().getInt(Const.POSITION)
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentAddBinding {
        return FragmentAddBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() {

        addItemsList()
        setAdapter(imageList)
        setClickListener()
        setData()
    }

    private fun setAdapter(imageList: MutableList<Int>) = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        adapter.addItem(imageList)
    }

    private fun setData() = with(binding) {
        id?.let {
            buttonCreate.setText(R.string.update)
            imageViewImage.setImageResource(MyQuotesFragment.data[it].image)
            editTextTitle.setText(MyQuotesFragment.data[it].title)
            editTextTitle.setText(MyQuotesFragment.data[it].title)
            editTextDes.setText(MyQuotesFragment.data[it].description)
            editTextLocation.setText(MyQuotesFragment.data[it].location)
            constraint.visibility = View.GONE
            isImageSelect = true
        }
    }

    private fun setClickListener() = with(binding) {
        imageViewBack.setOnClickListener {
            popBackStack()
        }

        buttonCreate.setOnClickListener { checkValidation() }
    }

    private fun checkValidation() = with(binding) {
        val title = editTextTitle.text.toString().trim()
        val description = editTextDes.text.toString().trim()
        val location = editTextLocation.text.toString().trim()

        when {
            !isImageSelect -> {
                showMessage(
                    requireContext().getString(R.string.validation_please_select_image)
                )
            }
            title.isEmpty() -> {
                showMessage(
                    requireContext().getString(R.string.validation_please_enter_title)
                )
                editTextTitle.requestFocus()
            }
            description.isEmpty() -> {
                showMessage(
                    requireContext().getString(R.string.validation_please_enter_a_description)
                )
                editTextDes.requestFocus()
            }
            location.isEmpty() -> {
                showMessage(
                    requireContext().getString(R.string.validation_please_enter_a_location)
                )
                editTextLocation.requestFocus()
            }
            else -> {
                if (id != null) {
                    updateData()
                } else {
                    requireActivity().supportFragmentManager.setFragmentResult(
                        Const.DATA,
                        bundleOf(
                            Const.IMAGE to imageList[imageId!!],
                            Const.TITLE to title,
                            Const.DESCRIPTION to description,
                            Const.LOCATION to location
                        )
                    )
                    popBackStack()

                }
            }
        }
    }


    private fun addItemsList() {
        imageList.add(R.drawable.image1)
        imageList.add(R.drawable.image2)
        imageList.add(R.drawable.image3)
        imageList.add(R.drawable.image4)
        imageList.add(R.drawable.image5)
        imageList.add(R.drawable.image6)
        imageList.add(R.drawable.image7)
        imageList.add(R.drawable.image8)
        imageList.add(R.drawable.image9)
        imageList.add(R.drawable.image10)
    }

    private fun updateData() = with(binding) {
        id?.let {
            MyQuotesFragment.data[it].title = editTextTitle.text.toString().trim()
            MyQuotesFragment.data[it].description = editTextDes.text.toString().trim()
            MyQuotesFragment.data[it].location = editTextLocation.text.toString().trim()
            popBackStack()
        }
    }

    override fun onClick(position: Int, v: View?) = with(binding) {
        imageId = position
        isImageSelect = true
        imageViewImage.setImageResource(imageList[position])
        constraint.visibility = View.GONE
    }
}