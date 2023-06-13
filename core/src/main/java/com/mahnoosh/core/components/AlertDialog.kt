package com.mahnoosh.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MyAlertDialog(
    isVisible: Boolean, title: String, content: String, onDismissRequested: () -> Unit
) {
    if (isVisible) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequested()
            },
            title = {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
            },
            confirmButton = {
                IconButton(onClick = { onDismissRequested() }) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                }
            },
            dismissButton = {
                IconButton(onClick = { onDismissRequested() }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }
            }
        )
    }
}