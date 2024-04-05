package com.mvvm.di.interfaces

import android.app.Application
import com.mvvm.ui.demoDagger.activity.SecondActivity
import com.mvvm.ui.demoDagger.activity.SharePrefActivity
import com.mvvm.di.module.AppModule
import com.mvvm.di.module.RetrofitModule
import com.mvvm.di.module.SharePrefModule
import com.mvvm.ui.activity.CountryListActivity
import com.mvvm.ui.base.BaseActivity
import com.mvvm.ui.dagger.activity.LoginActivity
import com.mvvm.ui.dagger.di.modul.NotificationServiceModule
import com.mvvm.ui.dagger.di.modul.UserRepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, UserRepositoryModule::class, NotificationServiceModule::class, SharePrefModule::class])
interface AppComponent {

    fun inject(baseActivity: BaseActivity)

//    @Component.Factory
//    interface Factory{
//        fun appilcation(@BindsInstance application: Application):AppComponent
//    }

    fun inject(loginActivity: LoginActivity)

    fun inject(countryListActivity: CountryListActivity)

    fun inject(sharePrefActivity: SharePrefActivity)

    fun inject(secondActivity: SecondActivity)
}