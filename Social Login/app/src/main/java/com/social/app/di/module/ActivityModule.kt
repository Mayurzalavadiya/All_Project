package com.social.app.di.module

import android.content.Context
import com.social.app.di.DiConstants
import com.social.app.ui.base.BaseActivity
import com.social.app.ui.manager.FragmentHandler
import com.social.app.ui.manager.FragmentManager
import com.social.app.ui.manager.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    @ActivityScoped
    internal fun provideBaseActivity(@ActivityContext context: Context): BaseActivity {
        return (context as BaseActivity)
    }

    @Provides
    @ActivityScoped
    internal fun provideNavigator(baseActivity: BaseActivity): Navigator {
        return baseActivity
    }

    @Provides
    @ActivityScoped
    internal fun provideFragmentHandler(fragmentManager: FragmentManager): FragmentHandler {
        return fragmentManager
    }

    @Provides
    @ActivityScoped
    @Named(DiConstants.PLACEHOLDER)
    internal fun providePlaceHolder(baseActivity: BaseActivity): Int {
        return baseActivity.findFragmentPlaceHolder()
    }
}