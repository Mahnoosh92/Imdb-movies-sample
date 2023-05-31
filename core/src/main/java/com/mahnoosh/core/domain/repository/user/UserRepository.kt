package com.mahnoosh.core.domain.repository.user

import com.google.firebase.auth.FirebaseUser
import com.mahnoosh.core.data.models.local.ResultWrapper

interface UserRepository {
    suspend fun getUser(): ResultWrapper<FirebaseUser?>
}