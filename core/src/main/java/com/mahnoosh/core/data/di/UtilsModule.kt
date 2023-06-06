package com.mahnoosh.core.data.di

import com.mahnoosh.core.utils.string.ContentRetrieval
import com.mahnoosh.core.utils.string.DefaultContentRetrieval
import com.mahnoosh.core.utils.validate.DefaultValidator
import com.mahnoosh.core.utils.validate.Validator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {
    @Binds
    abstract fun bindValidator(
        defaultValidator: DefaultValidator
    ): Validator

    @Binds
    abstract fun bindContentRetrieval(
        defaultContentRetrieval: DefaultContentRetrieval
    ): ContentRetrieval
}