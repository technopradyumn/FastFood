package com.technopradyumn.fastfood.presentation.view.AllScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.technopradyumn.fastfood.R
import com.technopradyumn.fastfood.presentation.view.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    onLogout: () -> Unit = {},
    onCartClick: () -> Unit = {},
) {
    val navController = rememberNavController()
    val userName = "John Doe"
    val userEmail = "johndoe@example.com"
    val profileImage = R.drawable.profile_ic // Use your profile picture resource

    // Wrap the content in a Scrollable Column
    ScrollableColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        // Profile Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Image(
                painter = painterResource(id = profileImage),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = userName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = userEmail,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        // Cart Section
        ProfileSection(
            title = "Go to Cart",
            icon = Icons.Filled.ShoppingCart,
            onClick = { onCartClick() }
        )

        // Profile Edit Section
        ProfileSection(
            title = "Edit Profile",
            icon = Icons.Filled.Edit,
            onClick = { }
        )

        // Order History Section
        ProfileSection(
            title = "Order History",
            icon = Icons.Filled.History,
            onClick = { }
        )

        // Delivery Address Section
        ProfileSection(
            title = "Delivery Address",
            icon = Icons.Filled.Home,
            onClick = { } // Navigate to Delivery Address Screen
        )

        // Payment Methods Section
        ProfileSection(
            title = "Payment Methods",
            icon = Icons.Filled.Payment,
            onClick = {  } // Navigate to Payment Methods Screen
        )

        // Notifications Section
        ProfileSection(
            title = "Notifications",
            icon = Icons.Filled.Notifications,
            onClick = {  } // Navigate to Notifications Screen
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Settings Section
        ProfileSection(
            title = "Settings",
            icon = Icons.Filled.Settings,
            onClick = {  }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Logout Button
        Button(
            onClick = { onLogout() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(
                text = "Logout",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

// Scrollable Column
@Composable
fun ScrollableColumn(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()) // Make the column scrollable
            .fillMaxSize(),
        content = content
    )
}

@Composable
fun ProfileSection(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFECECEC)) // Light gray background for sections
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$title Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}