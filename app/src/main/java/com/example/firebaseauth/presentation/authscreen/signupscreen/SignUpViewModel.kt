package com.example.firebaseauth.presentation.authscreen.signupscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.common.Resource
import com.example.firebaseauth.domain.usecases.SignUpUseCase
import com.example.firebaseauth.presentation.authscreen.AuthScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> get() = _signUpState

    private val _signUpScreenState = MutableStateFlow(AuthScreenState())
    val signUpScreenState : StateFlow<AuthScreenState> get() = _signUpScreenState


    fun onEvent(event: SignUpEvent) {
        when (event) {
            SignUpEvent.IsPassVisible -> {
                _signUpState.value =
                    _signUpState.value.copy(isPassVisible = !_signUpState.value.isPassVisible)
            }

            is SignUpEvent.UpdateMail -> {
                _signUpState.value = _signUpState.value.copy(email = event.email)
            }

            is SignUpEvent.UpdateName -> {
                _signUpState.value = _signUpState.value.copy(name = event.name)
            }

            is SignUpEvent.UpdatePass -> {
                _signUpState.value = _signUpState.value.copy(password = event.password)
            }

            SignUpEvent.SignUpUser -> {
                if(validateInput()){
                    signUpUser()
                }else{
                    _signUpScreenState.value = _signUpScreenState.value.copy(
                        error = "Please fill all the fields correctly."
                    )
                }
            }
        }
    }

    private fun signUpUser() {
        viewModelScope.launch {
            signUpUseCase(
                signUpState.value.email,
                signUpState.value.password,
                signUpState.value.name
            ).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _signUpScreenState.value =
                            _signUpScreenState.value.copy(
                                isLoading = false,
                                error = resource.error
                            )
                    }

                    Resource.Loading -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
            }
        }
    }
    private fun validateInput(): Boolean {
        return _signUpState.value.name.isNotEmpty() &&
                _signUpState.value.email.isNotEmpty() &&
                _signUpState.value.password.isNotEmpty()
    }
}