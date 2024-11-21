package com.technopradyumn.fastfood.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun PasswordVisibilityToggle(
    passwordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit
) {
    IconButton(onClick = onPasswordVisibilityToggle) {
        Icon(
            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
            contentDescription = "Toggle password visibility"
        )
    }
}