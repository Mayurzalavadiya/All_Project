package com.mvvm.ui.dagger.service

import android.util.Log
import javax.inject.Inject

interface NotificationService {
    fun sendMessage(from: String, to: String, messageBody: String)
}

class EmailService @Inject constructor() :NotificationService{

    override fun sendMessage(from: String, to: String, messageBody: String) {
        Log.e("TAG", "Send Email: $from $to  $messageBody")
    }
}

class MessageService :NotificationService{
    override fun sendMessage(from: String, to: String, messageBody: String) {
        Log.e("TAG", "Send Message:  $messageBody")
    }

}