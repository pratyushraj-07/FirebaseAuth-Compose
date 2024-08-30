package com.example.firebaseauth.presentation.authscreen

data class AuthScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)