package com.jaygoo.demo.fragments

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.rangeseekbar.app.databinding.FragmentSingleBinding
import com.rangeseekbar.app.ui.base.BaseFragment

class SingleSeekBarFragment: BaseFragment<FragmentSingleBinding>() {


	fun initView()= with(binding) {
		sbsingle1.setProgress(10f)
		sbsingle2.setProgress(20f)
		sbsingle3.setProgress(30f)
		sbsingle4.setProgress(40f)
		sbsingle4.setIndicatorTextDecimalFormat("0.00")
		sbsingle4.setIndicatorTextStringFormat("%s%%")
		sbsingle5.setIndicatorTextDecimalFormat("0")

		sbsingle6.setTypeface(Typeface.SANS_SERIF)
		sbsingle6.setOnRangeChangedListener(object : OnRangeChangedListener {
			override fun onRangeChanged(rangeSeekBar: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
				when {
					leftValue < 33.33 -> rangeSeekBar.leftSeekBar.setIndicatorText("store")
					leftValue < 66.66 -> rangeSeekBar.leftSeekBar.setIndicatorText("making")
					else -> rangeSeekBar.leftSeekBar.setIndicatorText("Delivering")
				}

				if (rangeSeekBar.rangeSeekBarState[0].isMin){
					rangeSeekBar.leftSeekBar.setIndicatorText("orders")
				}else if (rangeSeekBar.rangeSeekBarState[0].isMax){
					rangeSeekBar.leftSeekBar.setIndicatorText("arrived")
				}
			}

			override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

			}

			override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

			}

		})
	}

	override fun createViewBinding(
		inflater: LayoutInflater,
		container: ViewGroup?,
		attachToRoot: Boolean
	): FragmentSingleBinding {
		return FragmentSingleBinding.inflate(inflater,container,attachToRoot)
	}

	override fun bindData() {
		initView()
	}


}