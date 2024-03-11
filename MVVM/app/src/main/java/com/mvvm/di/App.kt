package com.mvvm.di

import android.app.Application
import com.mvvm.di.module.AppModule
import com.mvvm.di.interfaces.AppComponent
import com.mvvm.di.interfaces.DaggerAppComponent
import com.mvvm.di.module.RetrofitModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).retrofitModule(RetrofitModule()).build()

    }
}