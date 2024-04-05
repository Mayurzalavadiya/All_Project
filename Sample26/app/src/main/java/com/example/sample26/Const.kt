package com.example.sample26

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.example.sample26.services.ApiServices
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.concurrent.TimeUnit


object Const {
    private const val BASE_URL = "https://reqres.in/"
    private const val TIME = 30

    const val TOKEN = "token"


    private var retrofit: Retrofit? = null

    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    }


    private val okHttpClient by lazy {
        OkHttpClient().newBuilder()
            .connectTimeout(TIME.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME.toLong(), TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)

                when  {
                    response.code == 400 && response.body == null -> {

                        val notFoundMessage = "Data Not Found"

                        Log.e("API_ERROR", "Response Code: 404, Message: $notFoundMessage")

                        return@Interceptor response.newBuilder()
                            .body(okhttp3.ResponseBody.create(null, notFoundMessage))
                            .code(404)
                            .message(notFoundMessage)
                            .build()
                    }

                    response.code == 204 && response.body == null -> {

                        val successfulMessage = "Successful"

                        Log.e("API_ERROR", "Response Code: 204, Message: $successfulMessage")

                        return@Interceptor response.newBuilder()
                            .body(okhttp3.ResponseBody.create(null, successfulMessage))
                            .code(404)
                            .message(successfulMessage)
                            .build()
                    }

                    response.code == 401 && response.body == null -> {
                        val unauthorizedMessage = "Unauthorized"
                        Log.e("API_ERROR", "Response Code: 401, Message: $unauthorizedMessage")
                        return@Interceptor response.newBuilder()
                            .body(okhttp3.ResponseBody.create(null, unauthorizedMessage))
                            .code(401)
                            .message(unauthorizedMessage)
                            .build()
                    }

                    else -> {
                        return@Interceptor response
                    }
                }
            })

            .build()
    }

    fun create(): ApiServices {

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!.create(ApiServices::class.java)
    }

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


     fun moveToNextScreen(context: Context, nextClass: Class<*>) {
        val intent = Intent(context, nextClass)
        context.startActivity(intent)
    }
    private var progressDialog: Dialog? = null

    fun showProgressDialog(context: Context) {
        try {
            hideProgressDialog()
            val dialog = Dialog(context)
            progressDialog = dialog
            dialog.setCancelable(false)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.custom_progress_view)
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog.show()
        } catch (_: Exception) {

        }
    }

    fun hideProgressDialog() {
        try {
            if (progressDialog != null) {
                if (progressDialog!!.isShowing) {
                    progressDialog!!.dismiss()
                }
            }

        } catch (_: Exception) {
        }
        progressDialog = null
    }

    @SuppressLint("SimpleDateFormat")
    fun currentDateTime(format: String): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat(format)
        return formatter.format(time)
    }

}