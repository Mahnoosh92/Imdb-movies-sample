package com.mahnoosh.athentication.data.di

import com.mahnoosh.athentication.data.repository.DefaultAuthUserRepository
import com.mahnoosh.athentication.domain.repository.AuthUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {

    @Binds
    abstract fun bindAuthUserRepository(
        defaultAuthUserRepository: DefaultAuthUserRepository
    ): AuthUserRepository

}