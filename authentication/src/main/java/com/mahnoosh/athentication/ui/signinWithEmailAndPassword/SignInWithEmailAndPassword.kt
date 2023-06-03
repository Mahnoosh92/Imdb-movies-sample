package com.mahnoosh.athentication.ui.signinWithEmailAndPassword

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahnoosh.athentication.R
import com.mahnoosh.core.ui.commons.UserInput
import com.mahnoosh.core.ui.commons.UserInputTypes

@Composable
fun SignInWithEmailAndPassword(modifier: Modifier = Modifier) {

    var email = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }
    Box(
        modifier = modifier
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
                label = "Email"
            ) {
                email.value = it
            }
            Spacer(modifier = modifier.height(10.dp))
            UserInput(
                type = UserInputTypes.PASSWORD,
                Icons.Filled.Settings,
                label = "Password"
            ) {
                password.value = it
            }
            Spacer(modifier = modifier.height(10.dp))
            
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInWithEmailAndPassword() {
    SignInWithEmailAndPassword()
}