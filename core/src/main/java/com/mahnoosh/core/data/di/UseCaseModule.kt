package com.mahnoosh.core.data.di

import com.mahnoosh.core.data.repository.user.DefaultUserRepository
import com.mahnoosh.core.domain.repository.user.UserRepository
import com.mahnoosh.core.domain.usecase.user.DefaultUserUseCase
import com.mahnoosh.core.domain.usecase.user.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindUserUseCase(
        defaultUserUseCase: DefaultUserUseCase
    ): UserUseCase
}