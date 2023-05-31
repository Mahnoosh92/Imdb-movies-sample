package com.mahnoosh.core.data.datasource.local.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class DefaultUserDatasource @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    UserDatasource {
    override suspend fun getUser(): FirebaseUser? = firebaseAuth.currentUser
}