package com.mahnoosh.core.data.di

import com.mahnoosh.core.data.repository.user.DefaultUserRepository
import com.mahnoosh.core.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindUserRepository(
        defaultUserRepository: DefaultUserRepository
    ): UserRepository
}