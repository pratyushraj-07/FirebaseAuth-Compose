package com.example.firebaseauth.domain.usecases

import com.example.firebaseauth.common.Resource
import com.example.firebaseauth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SignUpUseCase (
    private val authRepository: AuthRepository
){
    operator fun invoke(
        email: String,
        password: String,
        name:String
    ): Flow<Resource<FirebaseUser>> = flow {

        emit(Resource.Loading)

        try {
            val authResult = authRepository.signUpWithEmail(email, password, name)
            emit(authResult)
        }catch (e:Exception){
            emit(Resource.Error("User creation failed: ${e.localizedMessage}"))
        }

    }.flowOn(Dispatchers.IO)

}