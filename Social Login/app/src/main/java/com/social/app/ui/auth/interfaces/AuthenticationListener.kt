package com.social.app.ui.auth.interfaces

interface AuthenticationListener {
    fun onTokenReceived(auth_token: String?)
}