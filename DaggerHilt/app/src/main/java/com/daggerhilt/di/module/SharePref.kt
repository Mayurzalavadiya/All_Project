package com.daggerhilt.di.module

import com.daggerhilt.ui.helper.MyPreference
import com.daggerhilt.ui.helper.MyPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SharePref {

//    @Singleton
//    @Binds
//    abstract fun getPreference(myPreferenceImpl: MyPreferenceImpl): MyPreference
}