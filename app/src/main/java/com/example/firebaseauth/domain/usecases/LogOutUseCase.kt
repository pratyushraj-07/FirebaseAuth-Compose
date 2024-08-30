package com.example.firebaseauth.domain.usecases

import com.example.firebaseauth.common.Resource
import com.example.firebaseauth.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
            emit(authRepository.logOut())
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}