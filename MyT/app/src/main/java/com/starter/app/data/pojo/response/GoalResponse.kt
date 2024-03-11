package com.starter.app.data.pojo.response


import com.google.gson.annotations.SerializedName

class GoalResponse : ArrayList<Goal>()

data class Goal(
    @SerializedName("achieved_value")
    val achievedValue: Double? = null,
    @SerializedName("color_code")
    val colorCode: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("goal_master_id")
    val goalMasterId: String? = null,
    @SerializedName("goal_measurement")
    val goalMeasurement: String? = null,
    @SerializedName("goal_name")
    val goalName: String? = null,
    @SerializedName("goal_value")
    var goalValue: Double? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("img_extn")
    val imgExtn: String? = null,
    @SerializedName("is_active")
    val isActive: String? = null,
    @SerializedName("is_deleted")
    val isDeleted: String? = null,
    @SerializedName("keys")
    val keys: String? = null,
    @SerializedName("mandatory")
    val mandatory: String? = null,
    @SerializedName("order_no")
    val orderNo: Double? = null,
    @SerializedName("patient_goal_rel_id")
    val patientGoalRelId: String? = null,
    @SerializedName("todays_achieved_value")
    val todaysAchievedValue: Double? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)
