package com.mahnoosh.athentication.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahnoosh.core.data.di.MainDispatcher
import com.mahnoosh.core.data.models.local.ResultWrapper
import com.mahnoosh.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _splashUiState = MutableStateFlow(SplashUiState())
    val splashUiState get() = _splashUiState.asStateFlow()

    val handler = CoroutineExceptionHandler { _, exception ->
        _splashUiState.update { splashUiState ->
            splashUiState.copy(error = exception.message)
        }
    }

    fun isUserLoggedIn() {
        viewModelScope.launch(handler + mainDispatcher) {
            delay(1500)
            when (val result = userUseCase.getUser()) {
                is ResultWrapper.Success -> {
                    _splashUiState.update { splashUiState ->
                        splashUiState.copy(isLoggedIn = (result.data==null).not())
                    }
                }

                is ResultWrapper.Error -> {
                    _splashUiState.update { splashUiState ->
                        splashUiState.copy(error = result.throwable.message)
                    }
                }
            }
        }
    }

    fun consumeIsLoading() {
        _splashUiState.update { splashUiState ->
            splashUiState.copy(isLoading = null)
        }
    }

    fun consumeIsLoggedIn() {
        _splashUiState.update { splashUiState ->
            splashUiState.copy(isLoggedIn = null)
        }
    }

    fun consumeError() {
        _splashUiState.update { splashUiState ->
            splashUiState.copy(error = null)
        }
    }
}

data class SplashUiState(
    val isLoading: Boolean? = null,
    val isLoggedIn: Boolean? = null,
    val error: String? = null
)