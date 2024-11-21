package com.technopradyumn.fastfood.presentation.view.AllScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.fastfood.R

@Composable
fun WelcomeScreen(
    onStartWithEmail: () -> Unit,
    continueWithFacebook: () -> Unit,
    continueWithGoogle: () -> Unit,
    onSignIn: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.welcome_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFEFEFEF).copy(alpha = 0.3f),
                            Color.White.copy(alpha = 0.5f),
                            Color(0xFFFCA06F),
                            Color(0xFFFCA06F)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        // Content Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Text
            Column() {

                Spacer(modifier = Modifier.height(200.dp))

                Text(
                    text = "Welcome to",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fast Food",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFCA06F) // Orange color
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Get your favorite meals delivered quickly right to your doorstep",
                    style = TextStyle(
                        color = Color.Black,
                    )
                )
            }
            
            Column {
                
                Button(
                    onClick = { onStartWithEmail() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = "Start with Email",
                        style = TextStyle(color = Color.White)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.5.dp),
                        color = Color.DarkGray
                    )
                    Text(
                        text = "Sign in with",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = TextStyle(color = Color.DarkGray)
                    )
                    Divider(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.5.dp),
                        color = Color.DarkGray
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = { continueWithFacebook()},
                    border = BorderStroke(0.dp, Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = "Facebook Logo",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Continue with Facebook",
                            style = TextStyle(color = Color.Black)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = { continueWithGoogle() },
                    border = BorderStroke(0.dp, Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = "Google Logo",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Continue with Google",
                            style = TextStyle(color = Color.Black)
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account?",
                    style = TextStyle(color = Color.Black),
                    modifier = Modifier.clickable { onSignIn() }
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign In",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable { onSignIn() }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomeScreenPreview() {
    WelcomeScreen(
        onStartWithEmail = {},
        continueWithFacebook = {},
        continueWithGoogle = {},
        onSignIn = {}
    )
}