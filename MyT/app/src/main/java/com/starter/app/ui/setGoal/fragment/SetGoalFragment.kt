package com.starter.app.ui.setGoal.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.starter.app.data.pojo.request.UpdateReadingGoalRequest
import com.starter.app.databinding.FragmentSetGoalBinding
import com.starter.app.di.DiConstants
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.setGoal.adapter.GoalListAdapter
import com.starter.app.ui.setGoal.adapter.ReadingListAdapter
import com.starter.app.ui.setGoal.viewmodel.GoalViewModel
import com.starter.app.ui.setGoal.viewmodel.ReadingViewModel
import com.starter.app.ui.setGoal.viewmodel.UpdateReadingGoalViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SetGoalFragment : BaseFragment<FragmentSetGoalBinding>() {

    private lateinit var updateReadingGoalData: UpdateReadingGoalRequest


    private val goalListAdapter by lazy { GoalListAdapter() }
    private val reaListAdapter by lazy { ReadingListAdapter() }

    private val goalViewModel: GoalViewModel by viewModels()
    private val readingViewModel: ReadingViewModel by viewModels()
    private val updateReadingGoalViewModel: UpdateReadingGoalViewModel by viewModels()


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentSetGoalBinding {
        return FragmentSetGoalBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun bindData() {
        setGoalAdapter()
        setReadingAdapter()
        apiCall()
        setClickListener()
    }

    private fun setReadingAdapter() = with(binding.recyclerviewReading) {
        adapter = reaListAdapter
        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setGoalAdapter() = with(binding.recyclerviewGoal) {
        adapter = goalListAdapter
        layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setClickListener() = with(binding) {

        imageViewBackArrow.setOnClickListener {
           navigator.goBack()
        }


        buttonSubmit.setOnClickListener {
            DiConstants.showProgressDialog(requireActivity())
            updateReadingGoalData = UpdateReadingGoalRequest(
                goalListAdapter.getGoalList(),
                reaListAdapter.getReadingList()
            )
            Log.e("TAG", "setClickListener: $updateReadingGoalData")
            callUpdateGoalApi()
        }
    }

    private fun callUpdateGoalApi() {
        updateReadingGoalViewModel.updateReadingGoal(updateReadingGoalData)
    }

    private fun apiCall() {
        DiConstants.showProgressDialog(requireActivity())
        goalViewModel.goal()
        readingViewModel.reading()
    }

    private fun observeLiveData() {
        goalViewModel.goalLiveData.observe(
            this, onChange = {
                DiConstants.hideProgressDialog()
                it.data?.let { data -> goalListAdapter.addList(data) }
            }, onError = {
                DiConstants.hideProgressDialog()
                return@observe true
            }
        )

        readingViewModel.readingLiveData.observe(
            this, {
                DiConstants.hideProgressDialog()
                it.data?.let { data -> reaListAdapter.addList(data) }
            }
        )


        updateReadingGoalViewModel.UpdateGoalLiveData.observe(this,
            onChange = {
                DiConstants.hideProgressDialog()
                showMessage(it.message)

            })
    }
}