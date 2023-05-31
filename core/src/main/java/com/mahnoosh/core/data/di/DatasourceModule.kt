package com.mahnoosh.core.data.di

import com.mahnoosh.core.data.datasource.local.user.DefaultUserDatasource
import com.mahnoosh.core.data.datasource.local.user.UserDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindUserDatasource(
        defaultUserDatasource: DefaultUserDatasource
    ): UserDatasource

}