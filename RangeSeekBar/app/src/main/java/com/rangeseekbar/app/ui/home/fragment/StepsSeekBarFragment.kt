package com.jaygoo.demo.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rangeseekbar.app.R
import com.rangeseekbar.app.databinding.FragmentStepBinding
import com.rangeseekbar.app.ui.base.BaseFragment
import java.util.ArrayList

class StepsSeekBarFragment : BaseFragment<FragmentStepBinding>() {

	fun initView() = with(binding) {
		val stepsDrawables = ArrayList<Int>()
		stepsDrawables.add(R.drawable.step_1)
		stepsDrawables.add(R.drawable.step_2)
		stepsDrawables.add(R.drawable.step_3)
		stepsDrawables.add(R.drawable.step_4)
		sbsteps1.setStepsDrawable(stepsDrawables)
		sbsteps2.setStepsDrawable(stepsDrawables)
	}

	override fun createViewBinding(
		inflater: LayoutInflater,
		container: ViewGroup?,
		attachToRoot: Boolean
	): FragmentStepBinding {
		return  FragmentStepBinding.inflate(inflater,container,attachToRoot)
	}

	override fun bindData() {
	initView()
	}


}