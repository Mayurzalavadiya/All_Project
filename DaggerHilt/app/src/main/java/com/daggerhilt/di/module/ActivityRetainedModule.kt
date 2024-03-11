package com.daggerhilt.di.module

import com.daggerhilt.ui.counter.activity.MyCounter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
//@InstallIn(ActivityComponent::class)
//@InstallIn(ActivityRetainedComponent::class)
@InstallIn(ViewModelComponent::class)
object ActivityRetainedModule {

//    @Provides
//    @ActivityScoped
//    fun getCounter(): MyCounter {
//        return MyCounter()
//    }

//    @Provides
//    @ActivityRetainedScoped
//    fun getCounter(): MyCounter {
//        return MyCounter()
//    }

    @Provides
    @ViewModelScoped
    fun getCounter(): MyCounter {
        return MyCounter()
    }
}