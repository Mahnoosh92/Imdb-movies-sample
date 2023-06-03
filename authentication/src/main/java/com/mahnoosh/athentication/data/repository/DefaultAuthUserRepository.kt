package com.mahnoosh.athentication.data.repository

import com.google.firebase.auth.AuthResult
import com.mahnoosh.athentication.data.datasource.remote.signin.AuthUserDataSource
import com.mahnoosh.athentication.domain.repository.AuthUserRepository
import javax.inject.Inject

class DefaultAuthUserRepository @Inject constructor(private val authUserDataSource: AuthUserDataSource) :
    AuthUserRepository {
    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult? =
        authUserDataSource.createUserWithEmailAndPassword(email = email, password = password)
}