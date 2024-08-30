package com.example.firebaseauth.presentation.authscreen.signupscreen

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPassVisible : Boolean = false,
)