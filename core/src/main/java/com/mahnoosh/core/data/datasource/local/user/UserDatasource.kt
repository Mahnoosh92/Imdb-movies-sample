package com.mahnoosh.core.data.datasource.local.user

import com.google.firebase.auth.FirebaseUser

interface UserDatasource {
    suspend fun getUser(): FirebaseUser?
}