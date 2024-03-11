package com.jaygoo.demo.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.jaygoo.widget.SeekBar
import com.jaygoo.widget.Utils
import com.rangeseekbar.app.R
import com.rangeseekbar.app.databinding.FragmentVerticalBinding
import com.rangeseekbar.app.ui.base.BaseFragment

class VerticalSeekBarFragment: BaseFragment<FragmentVerticalBinding>() {

	fun initView() = with(binding){

		sbvertical2.setIndicatorTextDecimalFormat("0.0")
		sbvertical2.setProgress(0f, 100f)
		changeSeekBarThumb(sbvertical2.leftSeekBar, sbvertical2.leftSeekBar.progress)
		changeSeekBarThumb(sbvertical2.rightSeekBar, sbvertical2.rightSeekBar.progress)
		sbvertical2.setOnRangeChangedListener(object : OnRangeChangedListener {
			override fun onRangeChanged(rangeSeekBar: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
				changeSeekBarThumb(rangeSeekBar.leftSeekBar, leftValue)
				changeSeekBarThumb(rangeSeekBar.rightSeekBar, rightValue)
			}

			override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

			}

			override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

			}

		})

		sbvertical3.setIndicatorTextDecimalFormat("0")
		sbvertical4.setIndicatorTextDecimalFormat("0")
		sbvertical4.setIndicatorTextStringFormat("%s%%")
		sbvertical4.setProgress(30f, 60.6f)

		sbvertical6.setProgress(30f)

		sbvertical7.setProgress(40f, 80f)

		sbvertical8.setIndicatorTextDecimalFormat("0.0")

		val stepsDrawables = ArrayList<Int>()
		stepsDrawables.add(R.drawable.step_1)
		stepsDrawables.add(R.drawable.step_2)
		stepsDrawables.add(R.drawable.step_3)
		stepsDrawables.add(R.drawable.step_1)
		sbvertical9.setStepsDrawable(stepsDrawables)
		changeSeekBarIndicator(sbvertical9.leftSeekBar, sbvertical9.leftSeekBar.progress)
		changeSeekBarIndicator(sbvertical9.rightSeekBar, sbvertical9.rightSeekBar.progress)
		sbvertical9.setOnRangeChangedListener(object : OnRangeChangedListener {
			override fun onRangeChanged(rangeSeekBar: RangeSeekBar, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
				changeSeekBarIndicator(rangeSeekBar.leftSeekBar, leftValue)
				changeSeekBarIndicator(rangeSeekBar.rightSeekBar, rightValue)
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
	): FragmentVerticalBinding {
		return FragmentVerticalBinding.inflate(layoutInflater,container,attachToRoot)
	}

	override fun bindData() {
		initView()
	}

		private fun changeSeekBarThumb(seekbar: SeekBar, value: Float){
            if (value < 33){
                seekbar.indicatorBackgroundColor = Utils.getColor(activity, R.color.colorAccent)
                seekbar.setThumbDrawableId(R.drawable.thumb_green, seekbar.thumbWidth, seekbar.thumbHeight)
            }else if (value < 66){
                seekbar.indicatorBackgroundColor = Utils.getColor(activity, R.color.colorProgress)
                seekbar.setThumbDrawableId(R.drawable.thumb_yellow, seekbar.thumbWidth, seekbar.thumbHeight)
            }else{
                seekbar.indicatorBackgroundColor = Utils.getColor(activity, R.color.colorRed)
                seekbar.setThumbDrawableId(R.drawable.thumb_red, seekbar.thumbWidth, seekbar.thumbHeight)
            }
        }


	private fun changeSeekBarIndicator(seekbar: SeekBar, value: Float){
		seekbar.showIndicator(true)
		if (Utils.compareFloat(value, 0f, 3) == 0 || Utils.compareFloat(value, 100f, 3) == 0){
			seekbar.setIndicatorText("smile")
		}else if (Utils.compareFloat(value, 100/3f, 3) == 0){
			seekbar.setIndicatorText("naughty")
		}else if (Utils.compareFloat(value, 200/3f, 3) == 0){
			seekbar.setIndicatorText("lovely")
		}else{
			seekbar.showIndicator(false)
		}
	}
}