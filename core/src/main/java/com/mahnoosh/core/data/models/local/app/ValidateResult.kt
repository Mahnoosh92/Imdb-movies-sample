package com.mahnoosh.core.data.models.local.app

import androidx.annotation.StringRes

data class ValidateResult(
    val isSuccess: Boolean,
    @StringRes val errorMessage: Int
)