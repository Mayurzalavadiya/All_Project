package com.daggerhilt.di.module

import com.daggerhilt.di.DiConstant
import com.daggerhilt.ui.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object  RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(DiConstant.TIME.toLong(), TimeUnit.MINUTES)
            .readTimeout(DiConstant.TIME.toLong(), TimeUnit.MINUTES)
            .writeTimeout(DiConstant.TIME.toLong(), TimeUnit.MINUTES)
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(getInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun getLoggingInterceptor(): Interceptor {
       return HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    }

    private fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)

            when {
                response.code == 404 && response.body == null -> {
                    val notFoundMessage = "Data Not Found"
//                    Log.e("API_ERROR", "Response Code: 404, Message: $notFoundMessage")
                    return@Interceptor response.newBuilder()
                        .body(ResponseBody.create(null, notFoundMessage))
                        .code(404)
                        .message(notFoundMessage)
                        .build()
                }

                response.code == 204 && response.body == null -> {
                    val successfulMessage = "Successful"
//                    Log.e("API_ERROR", "Response Code: 204, Message: $successfulMessage")
                    return@Interceptor response.newBuilder()
                        .body(ResponseBody.create(null, successfulMessage))
                        .code(404)
                        .message(successfulMessage)
                        .build()
                }

                response.code == 401 && response.body == null -> {
                    val unauthorizedMessage = "Unauthorized"
//                    Log.e("API_ERROR", "Response Code: 401, Message: $unauthorizedMessage")
                    return@Interceptor response.newBuilder()
                        .body(ResponseBody.create(null, unauthorizedMessage))
                        .code(401)
                        .message(unauthorizedMessage)
                        .build()
                }

                else -> {
                    return@Interceptor response
                }
            }
        }
    }
}