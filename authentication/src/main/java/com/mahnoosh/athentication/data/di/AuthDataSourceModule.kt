package com.mahnoosh.athentication.data.di

import com.mahnoosh.athentication.data.datasource.remote.signin.DefaultAuthUserDataSource
import com.mahnoosh.athentication.data.datasource.remote.signin.AuthUserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataSourceModule {

    @Binds
    abstract fun bindSignInDataSource(
        defaultSignInDataSource: DefaultAuthUserDataSource
    ): AuthUserDataSource

}