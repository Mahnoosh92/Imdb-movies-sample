package com.mahnoosh.athentication.ui.signinWithEmailAndPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahnoosh.athentication.domain.repository.AuthUserRepository
import com.mahnoosh.athentication.ui.splash.SplashUiState
import com.mahnoosh.core.data.di.MainDispatcher
import com.mahnoosh.core.data.models.local.app.ValidateResult
import com.mahnoosh.core.utils.string.ContentRetrieval
import com.mahnoosh.core.utils.validate.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInWithEmailAndPasswordViewModel @Inject constructor(
    private val authUserRepository: AuthUserRepository,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val validator: Validator,
    private val contentRetrieval: ContentRetrieval
) : ViewModel() {

    private val _signInWithEmailAndPasswordUiState =
        MutableStateFlow(SignInWithEmailAndPasswordUiState())
    val signInWithEmailAndPasswordUiState get() = _signInWithEmailAndPasswordUiState.asStateFlow()

    val handler = CoroutineExceptionHandler { _, exception ->
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(error = exception.message)
        }
    }

    fun getString(id: Int?) = id?.let { contentRetrieval.getString(id) }

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch(handler + mainDispatcher) {
            setLoadingStatus(true)
            delay(1500)
            authUserRepository.signInUserWithEmailAndPassword(email = email, password = password)
                ?.let { authResult ->
                    _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
                        signInWithEmailAndPasswordUiState.copy(isSignInSuccessful = true)
                    }
                }
        }
    }

    fun validateEmail(email: Flow<String>) {
        viewModelScope.launch(handler + mainDispatcher) {
            email.collect { email ->
                if (!email.isNullOrEmpty()) {
                    _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
                        signInWithEmailAndPasswordUiState.copy(
                            isEmailValidated = validator.validateEmail(
                                email
                            )
                        )
                    }
                }
            }
        }
    }

    fun validatePassword(password: Flow<String>) {
        viewModelScope.launch(handler + mainDispatcher) {
            password.collect { password ->
                if (!password.isNullOrEmpty()) {
                    _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
                        signInWithEmailAndPasswordUiState.copy(
                            isPasswordValidated = validator.validatePassword(
                                password
                            )
                        )
                    }
                }
            }
        }
    }

    private fun setLoadingStatus(loadingStatus: Boolean) {
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(isLoading = loadingStatus)
        }
    }

    fun consumeIsLoading() {
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(isLoading = null)
        }
    }

    fun consumeError() {
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(error = null)
        }
    }

    fun consumeIsSignInSuccessful() {
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(isSignInSuccessful = null)
        }
    }

    fun consumeIsEmailValidated() {
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(isEmailValidated = null)
        }
    }

    fun consumeIsPasswordValidated() {
        _signInWithEmailAndPasswordUiState.update { signInWithEmailAndPasswordUiState ->
            signInWithEmailAndPasswordUiState.copy(isPasswordValidated = null)
        }
    }
}

data class SignInWithEmailAndPasswordUiState(
    val isLoading: Boolean? = null,
    val error: String? = null,
    val isSignInSuccessful: Boolean? = null,
    val isEmailValidated: ValidateResult? = null,
    val isPasswordValidated: ValidateResult? = null
)