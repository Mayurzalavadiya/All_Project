package com.starter.app.ui.testTask.fragment

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.starter.app.R
import com.starter.app.databinding.FragmentAddBinding
import com.starter.app.exception.ApplicationException
import com.starter.app.ui.Const
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.testTask.adapter.GridAdapter
import com.starter.app.ui.testTask.interfaces.ClickListener
import com.starter.app.ui.testTask.pojo.MyQuotes
import com.starter.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(), ClickListener {

    private var isImageSelect = false
    private var id: Int? = null
    private var imageId: Int? = null
    private val imageList = mutableListOf<Int>()
    private val adapter by lazy {
        GridAdapter(this)
    }

    @Inject
    lateinit var validator: Validator

    private val isValid: Boolean
        get() {
            return try {
                if (!isImageSelect) {
                    throw ApplicationException(requireContext().getString(R.string.validation_please_select_image))
                }

                validator.submit(binding.editTextTitle)
                    .checkEmpty()
                    .errorMessage(requireContext().getString(R.string.validation_please_enter_title))
                    .check()

                validator.submit(binding.editTextDes)
                    .checkEmpty()
                    .errorMessage(requireContext().getString(R.string.validation_please_enter_a_description))
                    .check()

                validator.submit(binding.editTextLocation)
                    .checkEmpty()
                    .errorMessage(requireContext().getString(R.string.validation_please_enter_a_location))
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentAddBinding {
        return FragmentAddBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        id = arguments?.getString(Const.POSITION)?.toInt()
        addItemsList()
        setAdapter(imageList)
        setClickListener()
        setData()
    }

    private fun setAdapter(imageList: MutableList<Int>) = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
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
            navigator.goBack()
        }

        buttonCreate.setOnClickListener {
            if (isValid) {
                if (id != null) {
                    updateData()
                } else {
                    val myQuotes = MyQuotes(
                        imageList[imageId!!],
                        editTextTitle.text.toString(),
                        editTextDes.text.toString(),
                        editTextLocation.text.toString()
                    )
                    val intent = Intent()
                    intent.putExtra(Const.DATA, myQuotes)
                    requireActivity().setResult(Activity.RESULT_OK, intent)
                    requireActivity().finish()

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
            MyQuotesFragment.data[it].title = editTextTitle.text.toString()
            MyQuotesFragment.data[it].description = editTextDes.text.toString()
            MyQuotesFragment.data[it].location = editTextLocation.text.toString()
            requireActivity().finish()
        }
    }

    override fun onClick(position: Int, v: View?) = with(binding) {
        imageId = position
        isImageSelect = true
        imageViewImage.setImageResource(imageList[position])
        constraint.visibility = View.GONE
    }

}