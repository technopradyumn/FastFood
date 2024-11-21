package com.technopradyumn.fastfood.presentation.view.AllScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.fastfood.utils.OutlinedTextFieldWithError

@Composable
fun ForgotPasswordScreen(
    onBack: () -> Unit,
    onForgotPassword: () -> Unit,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf(false) }

    fun validateFields() {
        emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches() || !email.endsWith("@gmail.com")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Back Arrow in the top left corner
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 32.dp, start = 0.dp)
                .clickable(onClick = onBack)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }

        // Main content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = "Forgot Password",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextFieldWithError(
                placeholderText = "Your email",
                value = email,
                onValueChange = { email = it },
                label = "Email",
                isError = emailError,
                errorMessage = "Please enter a valid email (abc@gmail.com)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    validateFields()
                    if (!emailError) {
                        onForgotPassword()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Send ForgotPassword Link",
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }

        }
    }
}

@Preview
@Composable

fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(
        onBack = {},
        onForgotPassword = {}
    )
}