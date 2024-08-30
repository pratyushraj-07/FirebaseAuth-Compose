package com.example.firebaseauth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseauth.common.Resource
import com.example.firebaseauth.domain.usecases.LogoutUseCase
import com.example.firebaseauth.presentation.authscreen.AuthScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _profileState = MutableStateFlow(AuthScreenState())
    val profileState : StateFlow<AuthScreenState> get() = _profileState


    fun logOut(){
        viewModelScope.launch {
            logoutUseCase().collect{resource->
                when(resource){
                    is Resource.Error -> {
                        _profileState.value = _profileState.value.copy(
                            isLoading = false,
                            error = resource.error
                        )
                    }
                    Resource.Loading -> {
                        _profileState.value = _profileState.value.copy(
                            isLoading =  true
                        )
                    }
                    is Resource.Success -> {
                        _profileState.value = _profileState.value.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }
            }
        }
    }
}