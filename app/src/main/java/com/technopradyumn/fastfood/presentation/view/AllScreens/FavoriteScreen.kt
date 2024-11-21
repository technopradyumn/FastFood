package com.technopradyumn.fastfood.presentation.view.AllScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.technopradyumn.fastfood.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen() {
    var searchQuery by remember { mutableStateOf("") }

    // Mock data for favorite items
    val favoriteItems = listOf(
        FavoriteItem("Joe's Grill", "Joe's Grill", 15.99, "20 mins", 2.5, R.drawable.welcome_bg),
        FavoriteItem("Sushi Spot", "Sushi Spot", 20.50, "30 mins", 3.0, R.drawable.welcome_bg),
        FavoriteItem("Pizza Paradise", "Pizza Paradise", 18.75, "25 mins", 3.5, R.drawable.welcome_bg),
        FavoriteItem("Spicy Wings", "Wings Delight", 9.99, "15 mins", 1.2, R.drawable.welcome_bg),
        FavoriteItem("Burger Delight", "Burger King", 12.99, "20 mins", 1.8, R.drawable.welcome_bg),
        FavoriteItem("Pasta World", "Pasta Express", 14.50, "35 mins", 2.0, R.drawable.welcome_bg)
    )

    // Filtering items based on search query
    val filteredItems = favoriteItems.filter {
        it.name.contains(searchQuery, ignoreCase = true) || it.restaurantName.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Search Box
        OutlinedTextFieldWithError(
            placeholderText = "Search for food",
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = "Search",
            isError = false,
            errorMessage = "Search query must be at least 3 characters"
        )

        // Favorites Section Header
        Text(
            text = "Favorite Items",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Displaying the List of Favorite Items
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredItems) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White, shape = RoundedCornerShape(16.dp)) // Rounded corners for the card
                        .clip(RoundedCornerShape(16.dp)) // Ensures the corners are rounded
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = "${item.name} Image",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Restaurant: ${item.restaurantName}",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Price: \$${item.price}",
                                fontSize = 14.sp,
                                color = Color.Green
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Delivery Time: ${item.deliveryTime}",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Distance: ${item.distance} km",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

data class FavoriteItem(
    val name: String,
    val restaurantName: String, // Name of the restaurant
    val price: Double, // Price of the item
    val deliveryTime: String, // Delivery time (e.g., 20 mins)
    val distance: Double, // Distance in km
    val imageRes: Int // Resource ID of the image
)

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen()
}