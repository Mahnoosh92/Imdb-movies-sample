package com.mahnoosh.athentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mahnoosh.athentication.ui.theme.ImdbMoviesSampleAppTheme
import com.mahnoosh.dashboard.DashboardActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthActivity : ComponentActivity() {

    val myData = MutableStateFlow(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ImdbMoviesSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AuthNAvHost(navController = navController) {
                        myData.value = it
                    }
                    collector()
                }
            }
        }
    }

    @Composable
    fun collector() {
        val data = myData.collectAsState()
        if(data.value==true) {
            LocalContext.current.startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    @Composable
    fun AuthNAvHost(navController: NavHostController, changeData: (Boolean) -> Unit) {
        NavHost(navController = navController, startDestination = "auth") {
            composable("auth") {
                EmailSignIn() {
                    changeData(it)
                }
            }
        }
    }

    @Composable
    fun EmailSignIn(changeData: (Boolean) -> Unit) {
        Button(onClick = { changeData(true) }) {
            Text(text = "Sign In")
        }
    }
}






