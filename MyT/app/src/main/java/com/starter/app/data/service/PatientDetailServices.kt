package com.starter.app.data.service

import com.starter.app.data.URLFactory
import com.starter.app.data.pojo.ResponseBody
import com.starter.app.data.pojo.User
import com.starter.app.data.pojo.request.UpdateAddressRequest
import com.starter.app.data.pojo.request.UpdateProfileRequest
import com.starter.app.data.pojo.request.UpdateReadingGoalRequest
import com.starter.app.data.pojo.response.GoalResponse
import com.starter.app.data.pojo.response.ReadingResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PatientDetailServices {

    /**
     * API calling url and method
     */
    @POST(URLFactory.Method.GET_PATIENT_DETAILS)
    suspend fun getPatientDetails(): ResponseBody<User>

    @POST(URLFactory.Method.UPDATE_PROFILE)
    suspend fun updateProfile(@Body request: UpdateProfileRequest): ResponseBody<User>

    @POST(URLFactory.Method.UPDATE_PROFILE)
    suspend fun updateAddress(@Body request: UpdateAddressRequest): ResponseBody<User>

    @POST(URLFactory.Method.GOAL)
    suspend fun goal(): ResponseBody<GoalResponse>

    @POST(URLFactory.Method.READING)
    suspend fun reading(): ResponseBody<ReadingResponse>

    @POST(URLFactory.Method.UPDATE_READING_GOAL)
    suspend fun updateReadingGoal(@Body request: UpdateReadingGoalRequest): ResponseBody<Any>
}