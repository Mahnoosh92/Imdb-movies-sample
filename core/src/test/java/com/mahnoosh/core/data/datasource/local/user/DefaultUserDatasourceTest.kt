package com.mahnoosh.core.data.datasource.local.user

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DefaultUserDatasourceTest {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var defaultUserDatasource: DefaultUserDatasource

    @Before
    fun setUp() {
        defaultUserDatasource = DefaultUserDatasource(firebaseAuth = firebaseAuth)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test getUser`() = runTest {
        defaultUserDatasource.getUser()

        Mockito.verify(firebaseAuth).currentUser
    }
}