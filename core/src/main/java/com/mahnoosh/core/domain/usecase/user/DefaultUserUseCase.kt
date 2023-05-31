package com.mahnoosh.core.domain.usecase.user

import com.google.firebase.auth.FirebaseUser
import com.mahnoosh.core.data.models.local.ResultWrapper
import com.mahnoosh.core.domain.repository.user.UserRepository
import javax.inject.Inject

class DefaultUserUseCase @Inject constructor(private val userRepository: UserRepository) :
    UserUseCase {
    override suspend fun getUser(): ResultWrapper<FirebaseUser?> = userRepository.getUser()
}