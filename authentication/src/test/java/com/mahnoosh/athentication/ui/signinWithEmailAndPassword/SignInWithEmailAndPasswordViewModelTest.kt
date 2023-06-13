package com.mahnoosh.athentication.ui.signinWithEmailAndPassword

import com.mahnoosh.athentication.domain.repository.AuthUserRepository
import com.mahnoosh.core.utils.string.ContentRetrieval
import com.mahnoosh.core.utils.validate.Validator
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class SignInWithEmailAndPasswordViewModelTest {

    @Mock
    private lateinit var authUserRepository: AuthUserRepository

    @Mock
    private lateinit var validator: Validator

    @Mock
    private lateinit var contentRetrieval: ContentRetrieval

    private val testDistpatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}