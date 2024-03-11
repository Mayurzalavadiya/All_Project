package com.mvvm.ui.dagger.repository

import android.util.Log
import javax.inject.Inject


interface UserRepository {

    fun saveUser(email: String, password: String)
}

class SQLRepository @Inject constructor() : UserRepository {

    override fun saveUser(email: String, password: String) {
        Log.e("TAG", "Save data in SQL Data Base:")
    }
}

class FirebaseRepository : UserRepository {

    override fun saveUser(email: String, password: String) {
        Log.e("TAG", "Save data in Firebase Data Base:")
    }
}