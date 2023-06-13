package com.mahnoosh.athentication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AuthScreens(
    val route: String, val icon: ImageVector, val title: String, val path: String = ""
) {

    object Splash :
        AuthScreens(route = "splash_screen", icon = Icons.Filled.Person, title = "Splash")

    object SignUp :
        AuthScreens(route = "signup_screen", icon = Icons.Filled.AccountCircle, title = "SignUp")

    object SignInWithEmailAndPassword : AuthScreens(
        route = "sign_in_with_email_and_password_screen",
        icon = Icons.Filled.AccountCircle,
        title = "SignIn"
    )
}