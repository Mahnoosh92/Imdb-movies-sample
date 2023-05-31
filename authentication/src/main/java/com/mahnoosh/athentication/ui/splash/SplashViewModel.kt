package com.mahnoosh.athentication.ui.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _splashUiState = MutableStateFlow(SplashUiState())
    val splashUiState get() = _splashUiState.asStateFlow()

    fun isUserLoggedIn() {

    }

    fun consumeIsLoading() {
        _splashUiState.update { splashUiState->
            splashUiState.copy(isLoading = null)
        }
    }

    fun consumeIsLoggedIn() {
        _splashUiState.update { splashUiState->
            splashUiState.copy(isLoggedIn = null)
        }
    }

    fun consumeError() {
        _splashUiState.update { splashUiState->
            splashUiState.copy(error = null)
        }
    }
}

data class SplashUiState(
    val isLoading: Boolean? = null,
    val isLoggedIn: Boolean? = null,
    val error: String? = null
)