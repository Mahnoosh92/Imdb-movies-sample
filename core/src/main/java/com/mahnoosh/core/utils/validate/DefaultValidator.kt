package com.mahnoosh.core.utils.validate

import android.util.Patterns.EMAIL_ADDRESS
import android.util.Patterns.PHONE
import com.mahnoosh.core.R
import com.mahnoosh.core.data.models.local.app.ValidateResult
import java.util.regex.Pattern
import javax.inject.Inject

class DefaultValidator @Inject constructor() : Validator {
    override fun validatePhoneNumber(phone: String): ValidateResult {
        return validate(PhoneValidator(phone))
    }

    override fun validateEmail(email: String): ValidateResult {
        return validate(EmailValidator(email))
    }

    override fun validatePassword(password: String): ValidateResult {
        return validate(PasswordValidator(password))
    }

    private fun validate(vararg validators: IValidator): ValidateResult {
        validators.forEach {
            val result = it.validate()
            if (!result.isSuccess)
                return result
        }
        return ValidateResult(true, R.string.text_validation_success)
    }
}

class EmailValidator(private val email: String) : IValidator {
    override fun validate(): ValidateResult {
        val isValid =
            email.isNotEmpty() && EMAIL_ADDRESS.matcher(email).matches()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_email
        )
    }
}

class PasswordValidator(private val password: String) : IValidator {
    private val passwordPattern = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+!=])" + // at least 1 special character
                "(?=\\S+$)" + // no white spaces
                "(?=\\S+$)" + //no white spaces
                ".{6,}" + // at least 4 characters
                "$"
    )

    override fun validate(): ValidateResult {
        val isValid = password.isNotEmpty() && passwordPattern.matcher(password).matches()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_password
        )
    }
}

class PhoneValidator(private val phone: String) : IValidator {
    override fun validate(): ValidateResult {
        val isValid = PHONE.matcher(phone).matches()
        return ValidateResult(
            isValid,
            if (isValid) R.string.text_validation_success else R.string.text_validation_error_phone
        )
    }
}

interface IValidator {
    fun validate(): ValidateResult
}