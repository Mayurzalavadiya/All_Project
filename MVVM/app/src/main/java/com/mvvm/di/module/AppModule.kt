package com.mvvm.di.module

import android.app.Application
import android.content.Context
import com.mvvm.ui.demoDagger.activity.ToastGenerate
import com.mvvm.ui.demoDagger.activity.ToastImpl
import com.mvvm.ui.helper.MyPreference
import com.mvvm.ui.helper.MyPreferenceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Application {
        return application
    }

//    @Provides
//    @Singleton
//    fun getPreference(): MyPreference {
//        return MyPreferenceImpl(application)
//    }

    @Provides
    @Singleton
    fun getToastImpl():ToastImpl{
        return ToastImpl()
    }

    @Provides
    @Singleton
    fun getIntent(toastImpl: ToastImpl):ToastGenerate{
        return ToastGenerate(ToastImpl())
    }

    @Provides
    fun firstString():String ="hello First"

    @Provides
    @Named("Two")
    fun secondString():String ="hello Two"

}

