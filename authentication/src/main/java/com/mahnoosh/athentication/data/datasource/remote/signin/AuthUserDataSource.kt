package com.mahnoosh.athentication.data.datasource.remote.signin

import com.google.firebase.auth.AuthResult

interface AuthUserDataSource {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthResult?
}