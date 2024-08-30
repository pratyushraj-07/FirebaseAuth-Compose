package com.example.firebaseauth.presentation.authscreen.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.common.Resource
import com.example.firebaseauth.domain.usecases.LoginUseCase
import com.example.firebaseauth.presentation.authscreen.AuthScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(){

    private val _loginState = MutableStateFlow(LoginState())
    val loginState : StateFlow<LoginState> get() = _loginState

    private val _loginScreenState = MutableStateFlow(AuthScreenState())
    val loginScreenState : StateFlow<AuthScreenState> get() = _loginScreenState

    fun onEvent(event: LoginEvent){
        when(event){
            LoginEvent.IsPassVisible -> {
                _loginState.value = _loginState.value.copy(isPassVisible = !loginState.value.isPassVisible)
            }

            is LoginEvent.UpdateMail -> {
                _loginState.value = _loginState.value.copy(email = event.email)
            }

            is LoginEvent.UpdatePass -> {
                _loginState.value = _loginState.value.copy(password = event.password)
            }

            LoginEvent.LoginUser -> {
                if(validateInput()){
                    loginUser()
                }else{
                    _loginScreenState.value = _loginScreenState.value.copy(
                        error = "Please fill all the fields correctly."
                    )
                }
            }
        }
    }

    private fun loginUser() {
        viewModelScope.launch {
            loginUseCase(
                _loginState.value.email,
                _loginState.value.password
            ).collect{resource->
                when(resource){
                    is Resource.Error -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            error = resource.error
                        )
                    }
                    Resource.Loading -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        return _loginState.value.email.isNotEmpty() &&
                _loginState.value.password.isNotEmpty()
    }
}