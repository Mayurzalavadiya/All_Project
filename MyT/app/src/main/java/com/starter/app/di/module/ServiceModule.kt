package com.starter.app.di.module

import com.starter.app.data.datasource.UserLiveDataSource
import com.starter.app.data.repository.UserRepository
import com.starter.app.data.service.AuthenticationService
import com.starter.app.data.service.LogOutService
import com.starter.app.data.service.PatientDetailServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
    fun getPatientDetails(retrofit: Retrofit): PatientDetailServices {
        return retrofit.create(PatientDetailServices::class.java)
    }
    @Provides
    @Singleton
    fun provideLogOutService(retrofit: Retrofit): LogOutService {
        return retrofit.create(LogOutService::class.java)
    }
}