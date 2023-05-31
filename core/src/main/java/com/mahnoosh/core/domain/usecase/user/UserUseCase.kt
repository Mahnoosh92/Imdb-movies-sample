package com.mahnoosh.core.domain.usecase.user

import com.google.firebase.auth.FirebaseUser
import com.mahnoosh.core.data.models.local.ResultWrapper

interface UserUseCase {
    suspend fun getUser(): ResultWrapper<FirebaseUser?>
}