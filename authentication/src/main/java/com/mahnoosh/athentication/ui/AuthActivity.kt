package com.mahnoosh.athentication.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mahnoosh.athentication.ui.navigation.AuthNavGraph
import com.mahnoosh.core.data.models.local.app.AppThemeState
import com.mahnoosh.core.ui.theme.ComposeAppTheme
import com.mahnoosh.dashboard.DashboardActivity
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
                ManageAuthContent(modifier = Modifier, navController = navController)
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
    fun ManageAuthContent(modifier: Modifier = Modifier, navController: NavHostController) {
        val currentContext = LocalContext.current
        Scaffold(topBar = {}, bottomBar = {}) { paddings ->
            AuthNavGraph(modifier = modifier.padding(paddings), navController = navController) {
                currentContext.startActivity(
                    Intent(
                        this@AuthActivity,
                        DashboardActivity::class.java
                    )
                )
            }
        }
    }
}






