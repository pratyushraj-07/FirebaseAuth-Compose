package com.example.firebaseauth.domain.repository

import com.example.firebaseauth.common.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signUpWithEmail(email: String, password: String, name:String):Resource<FirebaseUser>
    suspend fun loginWithEmail(email: String, password: String): Resource<FirebaseUser>
    suspend fun logOut(): Resource<Unit>

}