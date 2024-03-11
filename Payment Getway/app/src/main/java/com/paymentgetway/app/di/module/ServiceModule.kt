package com.paymentgetway.app.di.module

import com.paymentgetway.app.data.datasource.UserLiveDataSource
import com.paymentgetway.app.data.repository.UserRepository
import com.paymentgetway.app.data.service.AuthenticationService
import com.paymentgetway.app.data.service.StripeService
import com.paymentgetway.app.di.DiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideUserRepository(userLiveDataSource: UserLiveDataSource): UserRepository {
        return userLiveDataSource
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun stripe(@Named(DiConstants.EPHEMERAL_KEYS) retrofit: Retrofit): StripeService {
        return retrofit.create(StripeService::class.java)
    }
}