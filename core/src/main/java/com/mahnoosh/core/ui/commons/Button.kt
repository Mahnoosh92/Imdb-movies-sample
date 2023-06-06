package com.mahnoosh.core.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingButton(
    text: String,
    showLoader: Boolean,
    isEnabled: Boolean = true,
    onClicked: () -> Unit
) {

    var loadingButtonState by remember(showLoader) {
        mutableStateOf(LoadingButtonState(showLoader = showLoader))
    }

    Button(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        onClick = {
            onClicked()
            loadingButtonState = loadingButtonState.copy(showLoader = true)
        },
        enabled = !loadingButtonState.showLoader && isEnabled
    ) {
        if (loadingButtonState.showLoader) {
            CircularProgressIndicator(modifier = Modifier.size(35.dp, 35.dp))
        } else {
            Text(text = text)
        }
    }

}

@Stable
data class LoadingButtonState(val showLoader: Boolean = false)