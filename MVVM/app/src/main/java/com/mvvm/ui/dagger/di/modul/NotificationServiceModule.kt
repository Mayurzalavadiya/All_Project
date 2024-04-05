package com.mvvm.ui.dagger.di.modul

import com.mvvm.ui.dagger.di.DiConstant
import com.mvvm.ui.dagger.di.annotation.MessageQualifier
import com.mvvm.ui.dagger.service.EmailService
import com.mvvm.ui.dagger.service.MessageService
import com.mvvm.ui.dagger.service.NotificationService
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class NotificationServiceModule {

    @MessageQualifier
    @Provides
    fun getMessageService(): NotificationService {
        return MessageService()
    }

    @Named(DiConstant.EMAIL)
    @Provides
    fun getEmailService(emailService: EmailService): NotificationService {
        return emailService
    }
}