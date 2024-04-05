package com.jaygoo.demo.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.jaygoo.widget.SeekBar
import com.rangeseekbar.app.R
import com.rangeseekbar.app.databinding.FragmentRangeBinding
import com.rangeseekbar.app.ui.base.BaseFragment


class RangeSeekBarFragment : BaseFragment<FragmentRangeBinding>() {

    fun initView() = with(binding) {
        sbrange1.setProgress(0f, 100f)
        changeSeekBarThumb(sbrange1.leftSeekBar, sbrange1.leftSeekBar.progress)
        changeSeekBarThumb(sbrange1.rightSeekBar, sbrange1.rightSeekBar.progress)
        sbrange1.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(
                rangeSeekBar: RangeSeekBar,
                leftValue: Float,
                rightValue: Float,
                isFromUser: Boolean
            ) {
                changeSeekBarThumb(rangeSeekBar.leftSeekBar, leftValue)
                changeSeekBarThumb(rangeSeekBar.rightSeekBar, rightValue)
            }

            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {

            }

        })

        sbrange2.setProgress(0f, 100f)

        sbrange3.setRange(-100f, 100f)
        sbrange3.setProgress(0f, 80f)
        sbrange3.setIndicatorTextDecimalFormat("0")

        sbrange4.setProgress(20f, 70f)

        sbrange5.setProgress(20f, 60f)

        sbrange6.setProgress(20f, 70f)

        sbrange8.setProgress(20f, 60f)
        sbrange8.leftSeekBar?.thumbDrawableId = R.drawable.step_1
        sbrange8.rightSeekBar?.thumbDrawableId = R.drawable.step_2

    }

    private fun changeSeekBarThumb(seekbar: SeekBar, value: Float) {
        if (value < 33) {
            seekbar.setThumbDrawableId(
                R.drawable.thumb_green,
                seekbar.thumbWidth,
                seekbar.thumbHeight
            )
        } else if (value < 66) {
            seekbar.setThumbDrawableId(
                R.drawable.thumb_yellow,
                seekbar.thumbWidth,
                seekbar.thumbHeight
            )
        } else {
            seekbar.setThumbDrawableId(R.drawable.thumb_red, seekbar.thumbWidth, seekbar.thumbHeight)
        }

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentRangeBinding {
        return FragmentRangeBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        initView()
    }
}