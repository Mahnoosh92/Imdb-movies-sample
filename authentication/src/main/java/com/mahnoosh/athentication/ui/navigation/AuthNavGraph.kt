package com.mahnoosh.athentication.ui.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mahnoosh.athentication.ui.signinWithEmailAndPassword.SignInWithEmailAndPassword
import com.mahnoosh.athentication.ui.signinWithEmailAndPassword.SignInWithEmailAndPasswordViewModel
import com.mahnoosh.athentication.ui.splash.Splash
import com.mahnoosh.athentication.ui.splash.SplashViewModel

@Composable
fun AuthNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigatingToDashboard: () -> Unit
) {
    NavHost(navController = navController, startDestination = AuthScreens.Splash.route) {
        composable(AuthScreens.Splash.route) {
            Splash(modifier = modifier, viewModel = hiltViewModel<SplashViewModel>()) { route ->
                if (route == null) {
                    navigatingToDashboard()
                } else {
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            }
        }
        composable(AuthScreens.SignInWithEmailAndPassword.route) {
            SignInWithEmailAndPassword(viewModel = hiltViewModel<SignInWithEmailAndPasswordViewModel>()) {
                navigatingToDashboard()
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}