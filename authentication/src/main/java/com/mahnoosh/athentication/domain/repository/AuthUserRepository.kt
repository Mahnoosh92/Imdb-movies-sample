package com.mahnoosh.athentication.domain.repository

import com.google.firebase.auth.AuthResult

interface AuthUserRepository {
    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthResult?
}