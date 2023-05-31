package com.mahnoosh.core.data.repository.user

import com.google.firebase.auth.FirebaseUser
import com.mahnoosh.core.data.datasource.local.user.UserDatasource
import com.mahnoosh.core.data.models.local.ResultWrapper
import com.mahnoosh.core.domain.repository.user.UserRepository
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(private val userDatasource: UserDatasource) :
    UserRepository {
    override suspend fun getUser(): ResultWrapper<FirebaseUser?> {
        return try {
            val result = userDatasource.getUser()
            ResultWrapper.Success(result)
        } catch (e: Exception) {
            ResultWrapper.Error(e)
        }
    }
}