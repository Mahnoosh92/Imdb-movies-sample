package com.mahnoosh.athentication.ui.splash

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseUser
import com.mahnoosh.core.data.models.local.ResultWrapper
import com.mahnoosh.core.domain.usecase.user.UserUseCase
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class SplashViewModelTest {

    @Mock
    private lateinit var userUseCase: UserUseCase

    @Mock
    private lateinit var firebaseUser: FirebaseUser

    private lateinit var splashViewModel: SplashViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        splashViewModel =
            SplashViewModel(userUseCase = userUseCase, mainDispatcher = testDispatcher)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test isLoggedIn status is set correctly`() = runTest(testDispatcher.scheduler) {
        Mockito.`when`(userUseCase.getUser()).thenReturn(ResultWrapper.Success(firebaseUser))
        splashViewModel.isUserLoggedIn()

        advanceUntilIdle()
        splashViewModel.splashUiState.test {
            assertThat(awaitItem()).isEqualTo(SplashUiState(isLoggedIn = true))
        }
    }

    @Test
    fun `test error content is set correctly`() = runTest(testDispatcher.scheduler) {
        val ex = Exception("something went wrong")
        Mockito.`when`(userUseCase.getUser()).thenReturn(ResultWrapper.Error(ex))
        splashViewModel.isUserLoggedIn()

        advanceUntilIdle()
        splashViewModel.splashUiState.test {
            assertThat(awaitItem()).isEqualTo(SplashUiState(error = ex.message))
        }
    }
}