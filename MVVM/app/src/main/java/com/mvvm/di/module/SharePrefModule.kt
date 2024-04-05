package com.mvvm.di.module

import com.mvvm.ui.helper.MyPreference
import com.mvvm.ui.helper.MyPreferenceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SharePrefModule {

    @Binds
    @Singleton
    abstract fun getPreference(myPreferenceImpl: MyPreferenceImpl): MyPreference
}