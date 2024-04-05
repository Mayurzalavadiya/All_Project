package com.starter.app.data

import okhttp3.HttpUrl

object URLFactory {

    // server details
    // https://api.mytatvadev.in/api/v7/patient/send_otp_signup
    //3333333352
    private const val IS_LOCAL = true
    private const val SCHEME = "https"
    private val HOST = if (IS_LOCAL) "api.mytatvadev.in" else "skkyn.com"
    private val API_PATH = if (IS_LOCAL) "api/v7/patient/" else "websitedata/api/v2/"
//    private val PORT = if (IS_LOCAL) 8082 else 8082

    fun provideHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
//                .port(PORT)
                .addPathSegments(API_PATH)
                .build()
    }


    // API Methods
    object Method {
        const val LOGIN = "user/login"
        const val SEND_OTP_SIGNUP = "send_otp_signup"
        const val LOGIN_SEND_OTP = "login_send_otp"
        const val LOGIN_VERIFY_OTP = "login_verify_otp"
        const val GET_PATIENT_DETAILS = "get_patient_details"
        const val UPDATE_PROFILE = "update_profile"
        const val LOG_OUT = "logout"
        const val GOAL = "medial_condition_goal_patient_rel_list"
        const val READING = "medical_condition_reading_patient_rel_list"
        const val UPDATE_READING_GOAL = "add_reading_goal"
    }

}
