package com.example.firebaseauth.domain.di

import com.example.firebaseauth.domain.repository.AuthRepository
import com.example.firebaseauth.domain.usecases.LoginUseCase
import com.example.firebaseauth.domain.usecases.LogoutUseCase
import com.example.firebaseauth.domain.usecases.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideLogOutUseCase(authRepository: AuthRepository): LogoutUseCase {
        return LogoutUseCase(authRepository)
    }

}