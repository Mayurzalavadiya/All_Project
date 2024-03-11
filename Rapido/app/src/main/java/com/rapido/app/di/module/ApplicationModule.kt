package com.rapido.app.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.rapido.app.core.AppSession
import com.rapido.app.core.Session
import com.rapido.app.di.DiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Named(DiConstants.CACHE)
    internal fun provideCacheDir(application: Application): File {
        return application.cacheDir
    }

    @Provides
    @Singleton
    internal fun provideResources(application: Application): Resources {
        return application.resources
    }

    @Provides
    @Singleton
    internal fun provideCurrentLocale(resources: Resources): Locale {
        val locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources.configuration.locales.get(0)
        } else {
            resources.configuration.locale
        }

        return locale
    }

    @Provides
    @Singleton
    internal fun provideApplicationContext(@ApplicationContext applicationContext: Context): Context {
        return applicationContext
    }

    @Provides
    @Singleton
    @Named(DiConstants.API_KEY)
    internal fun provideApiKey(): String {
        return "RAPIDO"
    }

    @Provides
    @Singleton
    internal fun provideSession(session: AppSession): Session = session

    @Provides
    @Singleton
    @Named(DiConstants.AES_KEY)
    internal fun provideAESKey(): String {
        return "ZNCMaJ45APQOquqN125lkJSBFBhanHJq"
    }
}