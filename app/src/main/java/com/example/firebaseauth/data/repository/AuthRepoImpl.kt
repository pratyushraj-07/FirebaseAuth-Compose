package com.example.firebaseauth.data.repository

import com.example.firebaseauth.common.Resource
import com.example.firebaseauth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthRepoImpl(
    private val fireBaseAuth : FirebaseAuth
) : AuthRepository {

    override suspend fun signUpWithEmail(
        email: String,
        password: String,
        name:String
    ): Resource<FirebaseUser> {
        return try {
            val authResult = fireBaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if(user != null) {
                val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(name).build()
                user.updateProfile(profileUpdates).await()
                Resource.Success(user)
            }else{
                Resource.Error("User creation failed")
            }
        }catch (e:Exception){
            Resource.Error("User creation failed: ${e.localizedMessage}")
        }catch (e: FirebaseAuthException){
            Resource.Error("User creation failed: ${e.localizedMessage}")
        }
    }

    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return try {
            val authResult = fireBaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = authResult.user
            if(user!=null){
                Resource.Success(user)
            }else{
                Resource.Error("Login failed: No user found")
            }
        }catch (e:Exception){
            Resource.Error("Login failed: ${e.message}")
        }catch (e: FirebaseAuthException){
            Resource.Error("Login Failed: ${e.localizedMessage}")
        }
    }

    override suspend fun logOut(): Resource<Unit> {
        return try {
            fireBaseAuth.signOut()
            Resource.Success(Unit)
        }catch (e:Exception){
            Resource.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

}