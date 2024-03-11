package com.starter.app.data.pojo.request


import com.google.gson.annotations.SerializedName

data class UpdateReadingGoalRequest(
    @SerializedName("goals")
    val goals: List<Goal?>? = null,
    @SerializedName("readings")
    val readings: List<Reading?>? = null
) {
    data class Goal(
        @SerializedName("goal_id")
        val goalId: String? = null,
        @SerializedName("goal_value")
        val goalValue: String? = null,
        @SerializedName("mandatory")
        val mandatory: String? = null
    )

    data class Reading(
        @SerializedName("mandatory")
        val mandatory: String? = null,
        @SerializedName("reading_id")
        val readingId: String? = null
    )
}