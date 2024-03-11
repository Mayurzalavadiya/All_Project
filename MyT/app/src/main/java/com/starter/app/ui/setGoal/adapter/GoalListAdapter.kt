package com.starter.app.ui.setGoal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.starter.app.data.pojo.request.UpdateReadingGoalRequest
import com.starter.app.data.pojo.response.Goal
import com.starter.app.data.pojo.response.GoalResponse
import com.starter.app.databinding.GoalListRecyclerViewBinding

class GoalListAdapter :
    RecyclerView.Adapter<GoalListAdapter.ViewHolder>() {

    private val goalList = mutableListOf<Goal>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(items: GoalResponse) {
        this.goalList.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: GoalListRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var id: Int? = null

        @SuppressLint("SetTextI18n")
        fun bind(item: Goal) = with(binding) {
            if (item.goalName == "Medication") {
                textViewEdit.visibility = View.GONE
            }
            Glide.with(imageview)
                .load(item.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageview)

            textviewTitleGoal.text = item.goalName
            textViewDecimal.text = item.goalMeasurement
            textviewSteps.text = item.goalValue?.toString()?.split(".")?.get(0) ?: "0"
            setClickListener(item)
        }

        private fun setClickListener(item: Goal) = with(binding) {
            textViewEdit.setOnClickListener {
                handleEditClick()
            }
            textViewUpdate.setOnClickListener {
                handleUpdateClick(item)
            }
        }

        private fun handleEditClick() = with(binding) {
            textViewEdit.visibility = View.GONE
            textviewSteps.visibility = View.GONE
            textViewUpdate.visibility = View.VISIBLE
            edittextSteps.visibility = View.VISIBLE
            edittextSteps.setText("")
            edittextSteps.requestFocus()
        }

        private fun handleUpdateClick(item: Goal) = with(binding) {

            if (!edittextSteps.text?.toString().isNullOrEmpty()) {
                val updateValue = edittextSteps.text.toString().toInt()
                if (isValidValue(itemView.context,item.goalName, updateValue)) {
                    textviewSteps.text = updateValue.toString()
                    item.goalValue = updateValue.toDouble()
                    edittextSteps.visibility = View.INVISIBLE
                    textViewUpdate.visibility = View.GONE
                    textviewSteps.visibility = View.VISIBLE
                    textViewEdit.visibility = View.VISIBLE
                } else {
                    edittextSteps.setText("")
                }
            } else{
                textViewEdit.visibility = View.VISIBLE
                textviewSteps.visibility = View.VISIBLE
                textViewUpdate.visibility = View.GONE
                edittextSteps.visibility = View.INVISIBLE
            }
        }
    }

    private fun isValidValue(context: Context,goalName: String?, value: Int): Boolean {
        val maxValue = when (goalName) {
            "Exercise" -> 600
            "Diet" -> 5000
            "Sleep" -> 24
            "Water" -> 100
            "Steps" -> 100000
            "Breathing" -> 600
            else -> 0
        }

        return if (value > maxValue) {
           Toast.makeText(context,"Please enter max value $maxValue", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GoalListRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = goalList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(goalList[position])
    }

    fun getGoalList(): MutableList<UpdateReadingGoalRequest.Goal> {
        val list = mutableListOf<UpdateReadingGoalRequest.Goal>()
        goalList.forEach {
            list.add(
                UpdateReadingGoalRequest.Goal(
                    it.goalMasterId, it.goalValue.toString(), it.mandatory
                )
            )
        }
        return list
    }
}
