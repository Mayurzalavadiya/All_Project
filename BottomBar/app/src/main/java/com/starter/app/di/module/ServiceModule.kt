package com.starter.app.di.module

import com.starter.app.data.datasource.UserLiveDataSource
import com.starter.app.data.repository.UserRepository
import com.starter.app.data.service.AuthenticationService
import com.starter.app.data.service.BitCoinService
import com.starter.app.di.DiConstants
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
    fun provideBitCoinService(@Named(DiConstants.BITCOIN) retrofit: Retrofit): BitCoinService {
        return retrofit.create(BitCoinService::class.java)
    }

}