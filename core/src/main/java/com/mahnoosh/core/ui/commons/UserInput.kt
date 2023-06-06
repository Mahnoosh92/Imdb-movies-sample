package com.mahnoosh.core.ui.commons

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

enum class UserInputTypes {
    TEXT, PASSWORD, EMAIL, NUMBER
}

@Composable
fun UserInput(
    type: UserInputTypes,
    leadingIcon: ImageVector,
    label: String,
    isError: Boolean,
    errorMessage: String,
    onInputChange: (String) -> Unit
) {
    var textFieldState by rememberSaveable(stateSaver = TextFieldValue.Companion.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }
    val keyBoardType = when (type) {
        UserInputTypes.TEXT -> {
            KeyboardType.Text
        }

        UserInputTypes.PASSWORD -> {
            KeyboardType.Password
        }

        UserInputTypes.EMAIL -> {
            KeyboardType.Email
        }

        UserInputTypes.NUMBER -> {
            KeyboardType.Number
        }
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldState,
        onValueChange = {
            textFieldState = it
            onInputChange(textFieldState.text)
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = null)
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
        label = {
            Text(text = label)
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUserInput() {
    UserInput(
        type = UserInputTypes.EMAIL,
        Icons.Filled.Email,
        "Email",
        isError = false,
        errorMessage = ""
    ) {}
}
