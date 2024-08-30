package com.example.firebaseauth.presentation.authscreen.signupscreen

sealed class SignUpEvent {

    data class UpdateName(val name: String) : SignUpEvent()
    data class UpdateMail(val email: String) : SignUpEvent()
    data class UpdatePass(val password: String) : SignUpEvent()
    data object IsPassVisible : SignUpEvent()
    data object SignUpUser: SignUpEvent()

}