package com.homehaven.app.di.module

import com.homehaven.app.core.AESCryptoInterceptor
import com.homehaven.app.core.Session
import com.homehaven.app.data.URLFactory
import com.homehaven.app.di.DiConstants
import com.homehaven.app.exception.AuthenticationException
import com.homehaven.app.exception.ServerError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(ApplicationModule::class)])
@InstallIn(SingletonComponent::class)
object NetModule {

    @Singleton
    @Provides
    internal fun provideClient(
        @Named(DiConstants.HEADER) headerInterceptor: Interceptor,
        @Named(DiConstants.PRE_VALIDATION) networkInterceptor: Interceptor,
        @Named(DiConstants.AES) aesInterceptor: Interceptor
    ): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor).addInterceptor(aesInterceptor)
            .addInterceptor(httpLoggingInterceptor).addNetworkInterceptor(networkInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES).build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(URLFactory.provideHttpUrl()).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    @Named(DiConstants.HEADER)
    internal fun provideHeaderInterceptor(session: Session): Interceptor {
        return Interceptor { chain ->
            val build = chain.request().newBuilder().addHeader(Session.API_KEY, session.apiKey)
                .addHeader(Session.USER_SESSION, session.userSession)
                .header(Session.LANGUAGE, session.language).build()
            chain.proceed(build)
        }
    }

    @Provides
    @Singleton
    @Named(DiConstants.PRE_VALIDATION)
    internal fun provideNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val code = response.code
            if (code >= 500) throw ServerError("Unknown server error", response.body!!.string())
            else if (code == 401 || code == 403) throw AuthenticationException()
            response
        }
    }

    @Provides
    @Singleton
    @Named(DiConstants.AES)
    internal fun provideAesCryptoInterceptor(aesCryptoInterceptor: AESCryptoInterceptor): Interceptor {
        return aesCryptoInterceptor
    }
}
