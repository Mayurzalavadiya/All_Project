package com.starter.app.data.pojo.response


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("message")
    val message: String? = null
) {
    data class Data(
        @SerializedName("account_role")
        val accountRole: String? = null,
        @SerializedName("active_deactive_id")
        val activeDeactiveId: String? = null,
        @SerializedName("address")
        val address: String? = null,
        @SerializedName("admin_users_id")
        val adminUsersId: Any? = null,
        @SerializedName("biometric_setting")
        val biometricSetting: Any? = null,
        @SerializedName("city")
        val city: String? = null,
        @SerializedName("contact_no")
        val contactNo: String? = null,
        @SerializedName("country")
        val country: String? = null,
        @SerializedName("country_code")
        val countryCode: String? = null,
        @SerializedName("created_at")
        val createdAt: String? = null,
        @SerializedName("current_status")
        val currentStatus: String? = null,
        @SerializedName("dob")
        val dob: String? = null,
        @SerializedName("email")
        val email: String? = null,
        @SerializedName("email_verified")
        val emailVerified: String? = null,
        @SerializedName("ethnicity")
        val ethnicity: Any? = null,
        @SerializedName("gender")
        val gender: String? = null,
        @SerializedName("greeting_text")
        val greetingText: String? = null,
        @SerializedName("height")
        val height: Any? = null,
        @SerializedName("height_unit")
        val heightUnit: String? = null,
        @SerializedName("is_accept_terms_accept")
        val isAcceptTermsAccept: String? = null,
        @SerializedName("is_active")
        val isActive: String? = null,
        @SerializedName("is_deleted")
        val isDeleted: String? = null,
        @SerializedName("language_name")
        val languageName: String? = null,
        @SerializedName("language_version")
        val languageVersion: Any? = null,
        @SerializedName("languages_id")
        val languagesId: String? = null,
        @SerializedName("last_active_date")
        val lastActiveDate: Any? = null,
        @SerializedName("last_login_date")
        val lastLoginDate: String? = null,
        @SerializedName("lock_till")
        val lockTill: Any? = null,
        @SerializedName("login_user")
        val loginUser: String? = null,
        @SerializedName("medical_condition_name")
        val medicalConditionName: List<MedicalConditionName?>? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("non_relevant")
        val nonRelevant: Any? = null,
        @SerializedName("password")
        val password: Any? = null,
        @SerializedName("patient_address_rel_id")
        val patientAddressRelId: Any? = null,
        @SerializedName("patient_age")
        val patientAge: Int? = null,
        @SerializedName("patient_guid")
        val patientGuid: Any? = null,
        @SerializedName("patient_id")
        val patientId: String? = null,
        @SerializedName("patient_link_doctor_details")
        val patientLinkDoctorDetails: List<PatientLinkDoctorDetail?>? = null,
        @SerializedName("patient_plans")
        val patientPlans: List<PatientPlan?>? = null,
        @SerializedName("pincode")
        val pincode: Any? = null,
        @SerializedName("profile_pic")
        val profilePic: String? = null,
        @SerializedName("relation")
        val relation: String? = null,
        @SerializedName("relevance_admin_users_id")
        val relevanceAdminUsersId: Any? = null,
        @SerializedName("relevant")
        val relevant: Any? = null,
        @SerializedName("restore_id")
        val restoreId: Any? = null,
        @SerializedName("severity_id")
        val severityId: Any? = null,
        @SerializedName("severity_name")
        val severityName: Any? = null,
        @SerializedName("state")
        val state: String? = null,
        @SerializedName("study_id")
        val studyId: Any? = null,
        @SerializedName("sub_relation")
        val subRelation: Any? = null,
        @SerializedName("token")
        val token: String? = null,
        @SerializedName("unread_notifications")
        val unreadNotifications: Int? = null,
        @SerializedName("updated_at")
        val updatedAt: String? = null,
        @SerializedName("updated_by")
        val updatedBy: String? = null,
        @SerializedName("user_from")
        val userFrom: String? = null,
        @SerializedName("weight")
        val weight: Any? = null,
        @SerializedName("weight_unit")
        val weightUnit: String? = null,
        @SerializedName("whatsapp_optin")
        val whatsappOptin: String? = null
    ) {
        data class MedicalConditionName(
            @SerializedName("medical_condition_name")
            val medicalConditionName: String? = null
        )

        data class PatientLinkDoctorDetail(
            @SerializedName("about")
            val about: String? = null,
            @SerializedName("access_code")
            val accessCode: String? = null,
            @SerializedName("added_by")
            val addedBy: Any? = null,
            @SerializedName("business_id")
            val businessId: Any? = null,
            @SerializedName("city")
            val city: Any? = null,
            @SerializedName("clinic_address")
            val clinicAddress: String? = null,
            @SerializedName("clinic_id")
            val clinicId: Any? = null,
            @SerializedName("clinic_name")
            val clinicName: String? = null,
            @SerializedName("contact_no")
            val contactNo: String? = null,
            @SerializedName("country")
            val country: String? = null,
            @SerializedName("country_code")
            val countryCode: String? = null,
            @SerializedName("created_at")
            val createdAt: String? = null,
            @SerializedName("deep_link")
            val deepLink: String? = null,
            @SerializedName("division")
            val division: Any? = null,
            @SerializedName("dob")
            val dob: Any? = null,
            @SerializedName("doctor_id")
            val doctorId: String? = null,
            @SerializedName("doctor_uniq_id")
            val doctorUniqId: Any? = null,
            @SerializedName("email")
            val email: String? = null,
            @SerializedName("experience")
            val experience: Any? = null,
            @SerializedName("gender")
            val gender: String? = null,
            @SerializedName("is_active")
            val isActive: String? = null,
            @SerializedName("is_deleted")
            val isDeleted: String? = null,
            @SerializedName("language_spoken")
            val languageSpoken: Any? = null,
            @SerializedName("languages_id")
            val languagesId: Any? = null,
            @SerializedName("medical_id_proof")
            val medicalIdProof: Any? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("patient_doctor_rel_id")
            val patientDoctorRelId: String? = null,
            @SerializedName("patient_id")
            val patientId: String? = null,
            @SerializedName("plan")
            val plan: Any? = null,
            @SerializedName("profile_image")
            val profileImage: String? = null,
            @SerializedName("qualification")
            val qualification: String? = null,
            @SerializedName("region")
            val region: String? = null,
            @SerializedName("source")
            val source: Any? = null,
            @SerializedName("specialization")
            val specialization: String? = null,
            @SerializedName("state")
            val state: Any? = null,
            @SerializedName("updated_at")
            val updatedAt: String? = null,
            @SerializedName("updated_by")
            val updatedBy: Any? = null
        )

        data class PatientPlan(
            @SerializedName("actual_price")
            val actualPrice: String? = null,
            @SerializedName("card_image")
            val cardImage: String? = null,
            @SerializedName("card_image_detail")
            val cardImageDetail: String? = null,
            @SerializedName("cloned_by")
            val clonedBy: Any? = null,
            @SerializedName("colour_scheme")
            val colourScheme: String? = null,
            @SerializedName("created_at")
            val createdAt: String? = null,
            @SerializedName("deep_link")
            val deepLink: Any? = null,
            @SerializedName("description")
            val description: String? = null,
            @SerializedName("discount_percentage")
            val discountPercentage: String? = null,
            @SerializedName("enable_rent_buy")
            val enableRentBuy: String? = null,
            @SerializedName("gst_percentage")
            val gstPercentage: Any? = null,
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("is_active")
            val isActive: String? = null,
            @SerializedName("is_deleted")
            val isDeleted: String? = null,
            @SerializedName("patient_id")
            val patientId: String? = null,
            @SerializedName("plan_master_id")
            val planMasterId: String? = null,
            @SerializedName("plan_name")
            val planName: String? = null,
            @SerializedName("plan_type")
            val planType: String? = null,
            @SerializedName("renewal_reminder_days")
            val renewalReminderDays: Int? = null,
            @SerializedName("show_book_device")
            val showBookDevice: String? = null,
            @SerializedName("show_home")
            val showHome: String? = null,
            @SerializedName("show_nutritionist")
            val showNutritionist: String? = null,
            @SerializedName("show_physio")
            val showPhysio: String? = null,
            @SerializedName("show_psycho")
            val showPsycho: String? = null,
            @SerializedName("start_at")
            val startAt: String? = null,
            @SerializedName("sub_title")
            val subTitle: String? = null,
            @SerializedName("updated_at")
            val updatedAt: String? = null,
            @SerializedName("updated_by")
            val updatedBy: String? = null,
            @SerializedName("vendor_test_flag")
            val vendorTestFlag: Any? = null,
            @SerializedName("what_to_expect")
            val whatToExpect: String? = null
        )
    }
}