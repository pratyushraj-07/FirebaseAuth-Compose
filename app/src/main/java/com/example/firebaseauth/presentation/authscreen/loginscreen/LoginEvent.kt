package com.example.firebaseauth.presentation.authscreen.loginscreen

sealed class LoginEvent {

    data class UpdateMail (val email: String): LoginEvent()
    data class UpdatePass (val password: String): LoginEvent()
    data object IsPassVisible : LoginEvent()
    data object LoginUser: LoginEvent()

}