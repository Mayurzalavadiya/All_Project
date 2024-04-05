package com.homey.app.ui.home.settings.model

data class FAQData(
    val question: String,
    val answer: String,
    var isExpandable: Boolean = false
)
