package com.example.firebaseauth.domain.usecases

import com.example.firebaseauth.common.Resource
import com.example.firebaseauth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginUseCase (
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        email:String,
        password:String
    ): Flow<Resource<FirebaseUser>> = flow {

        emit(Resource.Loading)

        try {
            val result = authRepository.loginWithEmail(email,password)
            emit(result)
        }catch (e:Exception){
            emit(Resource.Error("Login Failed: ${e.localizedMessage}"))
        }

    }.flowOn(Dispatchers.IO)
}