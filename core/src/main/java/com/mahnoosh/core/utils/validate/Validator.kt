package com.mahnoosh.core.utils.validate

import com.mahnoosh.core.data.models.local.app.ValidateResult

interface Validator {
    fun validatePhoneNumber(phone: String): ValidateResult
    fun validateEmail(email: String): ValidateResult
    fun validatePassword(password: String): ValidateResult
}