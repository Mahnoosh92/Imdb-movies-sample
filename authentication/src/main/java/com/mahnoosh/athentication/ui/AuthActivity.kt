package com.mahnoosh.athentication.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mahnoosh.athentication.ui.signinWithEmailAndPassword.SignInWithEmailAndPassword
import com.mahnoosh.athentication.ui.signinWithEmailAndPassword.SignInWithEmailAndPasswordViewModel
import com.mahnoosh.athentication.ui.splash.Splash
import com.mahnoosh.core.data.models.local.app.AppThemeState
import com.mahnoosh.core.ui.theme.ComposeAppTheme
import com.mahnoosh.dashboard.DashboardActivity
import com.mahnoosh.navigation.NavigationDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDark = isSystemInDarkTheme()
            val appTheme =
                remember { mutableStateOf(AppThemeState(darkTheme = isDark)) }

            val navController = rememberNavController()
            BaseView(appTheme.value) {
                AuthNAvHost(navController = navController) {
                    LocalContext.current.startActivity(Intent(this, DashboardActivity::class.java))
                }
            }
        }
    }

    @Composable
    fun BaseView(
        appThemeState: AppThemeState, content: @Composable () -> Unit
    ) {
        ComposeAppTheme(
            darkTheme = appThemeState.darkTheme, colorPallet = appThemeState.pallet
        ) {
            content()
        }
    }

    @Composable
    fun AuthNAvHost(navController: NavHostController, NavigateToFlow: @Composable () -> Unit) {
        NavHost(
            navController = navController,
            startDestination = NavigationDirections.Auth.splash.destination
        ) {
            composable(NavigationDirections.Auth.splash.destination) {
                Splash() {
                    navController.navigate(it) {
                        popUpTo(NavigationDirections.Auth.splash.destination) { inclusive = true }
                    }
                }
            }
            composable(NavigationDirections.Auth.signInWithEmailAndPassword.destination) {
                SignInWithEmailAndPassword(viewModel = hiltViewModel<SignInWithEmailAndPasswordViewModel>()) {
                    NavigateToFlow()
                }
            }
        }
    }
}






