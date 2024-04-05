package com.homey.app.ui.home.settings.model

data class AddMoneyData(
    val cardNumber: String,
    val nameOfHolder: String? = null,
    val expDate: String? = null,
    val cvv: String? = null,
)
