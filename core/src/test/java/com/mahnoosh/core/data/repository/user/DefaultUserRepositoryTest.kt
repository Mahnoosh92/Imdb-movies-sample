package com.mahnoosh.core.data.repository.user

import com.google.common.truth.Truth.assertThat
import com.mahnoosh.core.data.datasource.local.user.UserDatasource
import com.mahnoosh.core.data.models.local.ResultWrapper
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DefaultUserRepositoryTest {

    @Mock
    private lateinit var userDatasource: UserDatasource

    private lateinit var defaultUserRepository: DefaultUserRepository

    @Before
    fun setUp() {
        defaultUserRepository = DefaultUserRepository(userDatasource = userDatasource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test getUser is successful`() = runTest {

        Mockito.`when`(userDatasource.getUser()).thenThrow(NullPointerException::class.java)
        val user = defaultUserRepository.getUser()

        assertThat(user).isInstanceOf(ResultWrapper.Error::class.java)
    }

    @Test
    fun `test getUser is unsuccessful`() {

    }
}