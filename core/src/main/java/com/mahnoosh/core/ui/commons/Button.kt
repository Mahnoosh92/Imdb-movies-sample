package com.mahnoosh.core.ui.commons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingButton(text: String, loadingButtonState: LoadingButtonState) {


}

class LoadingButtonState() {
    var isLoading = false

    fun setLoading(loadingStatus: Boolean) {
        isLoading = loadingStatus
    }

}