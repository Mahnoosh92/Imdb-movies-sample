package com.mahnoosh.athentication.ui.signinWithEmailAndPassword

import androidx.lifecycle.ViewModel
import com.mahnoosh.athentication.domain.repository.AuthUserRepository
import com.mahnoosh.athentication.ui.splash.SplashUiState
import com.mahnoosh.core.data.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInWithEmailAndPasswordViewModel @Inject constructor(
    private val authUserRepository: AuthUserRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _signInWithEmailAndPasswordUiState =
        MutableStateFlow(SignInWithEmailAndPasswordUiState())
    val signInWithEmailAndPasswordUiState get() = _signInWithEmailAndPasswordUiState.asStateFlow()

    val handler = CoroutineExceptionHandler { _, exception ->
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(error = exception.message)
        }
    }



}

data class SignInWithEmailAndPasswordUiState(
    val isLoading: Boolean? = null,
    val error: String? = null,
    val isSignInSuccessful: Boolean? = null
)