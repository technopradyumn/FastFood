package com.technopradyumn.fastfood.presentation.view.AllScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.fastfood.R
import com.technopradyumn.fastfood.utils.DividerWithText
import com.technopradyumn.fastfood.utils.OutlinedTextFieldWithError
import com.technopradyumn.fastfood.utils.PasswordVisibilityToggle
import com.technopradyumn.fastfood.utils.SocialSignInButton
import com.technopradyumn.fastfood.utils.TextWithClickableAction

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit,
    onContinueWithFacebook: () -> Unit,
    onContinueWithGoogle: () -> Unit,
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var emailError by rememberSaveable { mutableStateOf(false) }
    var passwordError by rememberSaveable { mutableStateOf(false) }

    fun validateFields() {
        emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")
        passwordError = password.length <= 8
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Text(
                text = "Login",
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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextFieldWithError(
                placeholderText = "Password",
                value = password,
                onValueChange = { password = it },
                label = "Password",
                isError = passwordError,
                errorMessage = "Password must be more than 8 characters",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    PasswordVisibilityToggle(
                        passwordVisible = passwordVisible,
                        onPasswordVisibilityToggle = { passwordVisible = !passwordVisible }
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    color = Color(0xFFFF9800),
                    modifier = Modifier.clickable{onForgotPassword()}
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    validateFields()
                    if (!emailError && !passwordError) {
                        onSignUp()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextWithClickableAction(
                staticText = "Don't have an account?",
                actionText = "Sign Up",
                onClick = onSignUp
            )

            Spacer(modifier = Modifier.height(8.dp))

            DividerWithText("Sign in with")

            SocialSignInButton(
                text = "Continue with Facebook",
                icon = R.drawable.app_logo,
                onClick = onContinueWithFacebook
            )

            Spacer(modifier = Modifier.height(12.dp))

            SocialSignInButton(
                text = "Continue with Google",
                icon = R.drawable.app_logo,
                onClick = onContinueWithGoogle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen({}, {}, {}, {}, {})
}