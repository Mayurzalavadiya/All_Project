package com.mvvm.ui.dagger.di.modul

import com.mvvm.ui.dagger.repository.SQLRepository
import com.mvvm.ui.dagger.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class  UserRepositoryModule {

    @Binds
    abstract fun getSQLRepository(sqlRepository: SQLRepository): UserRepository
}