package com.mvvm.ui.dagger.service

import com.mvvm.ui.dagger.di.annotation.MessageQualifier
import com.mvvm.ui.dagger.repository.UserRepository
import javax.inject.Inject

class UserRegistrationService @Inject constructor(
    private val userRepository: UserRepository,
//    private val emailService: EmailService
    @MessageQualifier private val notificationService: NotificationService
) {


    fun registration(email: String, password: String) {
        userRepository.saveUser(email, password)
//        emailService.sendMessage("aspl@gmail.com", "User@gmail.com", "Registration Successfully ")
        notificationService.sendMessage(
            "aspl@gmail.com",
            "User@gmail.com",
            "Registration Successfully "
        )
    }
}