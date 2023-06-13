package com.mahnoosh.athentication.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mahnoosh.athentication.ui.navigation.AuthScreens

@Composable
fun Splash(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navigate: (String?) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.isUserLoggedIn()
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = modifier.height(10.dp))

            CircularProgressIndicator(modifier = modifier.size(80.dp))
        }
    }
    val splashUiState by viewModel.splashUiState.collectAsState()
    if (splashUiState.isLoggedIn == true) {
        navigate(null)

    } else {
        navigate(AuthScreens.SignInWithEmailAndPassword.route)
    }
    viewModel.consumeIsLoggedIn()
}

@Preview(showBackground = true)
@Composable
fun PreviewSplash() {
    Splash(viewModel = hiltViewModel()) {}
}