package com.daggerhilt.di.module

import android.content.Context
import android.util.Log
import com.daggerhilt.ui.helper.MyPreference
import com.daggerhilt.ui.helper.MyPreferenceImpl
import com.daggerhilt.ui.singleton.ToastGenerate
import com.daggerhilt.ui.singleton.ToastImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    internal fun provideApplicationContext(@ApplicationContext applicationContext: Context): Context {
        return applicationContext
    }

    @Provides
    @Singleton
    fun getPreference(@ApplicationContext applicationContext: Context): MyPreference {
        Log.e("TAG", "getPreference: ")
        return MyPreferenceImpl(applicationContext)
    }

    @Provides
    fun toastGenerate(): ToastGenerate {
        Log.e("TAG", "toastGenerate: ")
        return ToastGenerate(ToastImpl())
    }
}

