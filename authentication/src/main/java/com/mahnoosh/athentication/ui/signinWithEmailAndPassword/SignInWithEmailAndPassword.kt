package com.mahnoosh.athentication.ui.signinWithEmailAndPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahnoosh.athentication.R
import com.mahnoosh.athentication.ui.navigation.AuthScreens
import com.mahnoosh.core.ui.commons.LoadingButton
import com.mahnoosh.core.ui.commons.UserInput
import com.mahnoosh.core.ui.commons.UserInputTypes

@Composable
fun SignInWithEmailAndPassword(
    modifier: Modifier = Modifier,
    viewModel: SignInWithEmailAndPasswordViewModel,
    navigateToDashboard: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val signInWithEmailAndPasswordUiState by viewModel.signInWithEmailAndPasswordUiState.collectAsState()

    signInWithEmailAndPasswordUiState.isSignInSuccessful?.let {
        viewModel.apply {
            consumeError()
            consumeIsEmailValidated()
            consumeIsLoading()
            consumeIsSignInSuccessful()
            consumeIsPasswordValidated()
            navigateToDashboard()
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.validateEmail(email = snapshotFlow { email })
        viewModel.validatePassword(password = snapshotFlow { password })
    }

    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize()
            .padding(4.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = null,
                modifier = modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = "Enter your email and password",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = modifier.height(10.dp))
            UserInput(
                type = UserInputTypes.EMAIL,
                Icons.Filled.Email,
                label = "Email",
                isError = signInWithEmailAndPasswordUiState.isEmailValidated?.isSuccess?.not()
                    ?: false,
                errorMessage = viewModel.getString(signInWithEmailAndPasswordUiState.isEmailValidated?.errorMessage)
                    ?: ""
            ) {
                email = it
            }
            Spacer(modifier = modifier.height(10.dp))
            UserInput(
                type = UserInputTypes.PASSWORD,
                Icons.Filled.Settings,
                label = "Password",
                isError = signInWithEmailAndPasswordUiState.isPasswordValidated?.isSuccess?.not()
                    ?: false,
                errorMessage = viewModel.getString(signInWithEmailAndPasswordUiState.isPasswordValidated?.errorMessage)
                    ?: ""
            ) {
                password = it
            }
            Spacer(modifier = modifier.height(10.dp))
            LoadingButton(
                "SignIn",
                isEnabled = (signInWithEmailAndPasswordUiState.isEmailValidated?.isSuccess ?: false && signInWithEmailAndPasswordUiState.isPasswordValidated?.isSuccess ?: false),
                showLoader = signInWithEmailAndPasswordUiState.isLoading ?: false
            ) {
                viewModel.signInWithEmailAndPassword(email, password)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInWithEmailAndPassword() {
    SignInWithEmailAndPassword(viewModel = hiltViewModel<SignInWithEmailAndPasswordViewModel>()) {}
}