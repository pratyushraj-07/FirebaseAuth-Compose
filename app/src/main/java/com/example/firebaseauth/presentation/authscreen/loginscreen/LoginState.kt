package com.example.firebaseauth.presentation.authscreen.loginscreen

data class LoginState(
    val email : String = "",
    val password: String = "",
    val isPassVisible : Boolean = false,
)