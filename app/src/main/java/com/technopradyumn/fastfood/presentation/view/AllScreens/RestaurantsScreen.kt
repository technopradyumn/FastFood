package com.technopradyumn.fastfood.presentation.view.AllScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.fastfood.R
import com.technopradyumn.fastfood.data.model.Restaurant
import com.technopradyumn.fastfood.utils.SectionHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantsScreen() {
    var restaurantList by remember { mutableStateOf(emptyList<Restaurant>()) }
    var topRatedRestaurants by remember { mutableStateOf(emptyList<Restaurant>()) }

    // Fetch data (mocked here for simplicity)
    LaunchedEffect(Unit) {
        restaurantList = fetchRestaurants()
        topRatedRestaurants = fetchTopRatedRestaurants()
    }

    Spacer(modifier = Modifier.height(32.dp))

    // Wrap everything in LazyColumn to make it scrollable
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Top Rated Restaurants Section

        item{
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            SectionHeader(title = "Top Restaurants")
            TopRatedRestaurants(topRatedRestaurants)
            Spacer(modifier = Modifier.height(16.dp))
        }

        // All Restaurants Section
        item {
            SectionHeader(title = "All Restaurants")
        }
        items(restaurantList) { restaurant ->
            RestaurantItem(restaurant)
        }
        item{
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Composable
fun TopRatedRestaurants(topRatedRestaurants: List<Restaurant>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(topRatedRestaurants) { restaurant ->
            RestaurantCard(restaurant)
        }
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = restaurant.imageRes),
                contentDescription = "${restaurant.name} Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = restaurant.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = restaurant.location,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rating: ${restaurant.rating}",
                    fontSize = 14.sp,
                    color = Color.Green
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun RestaurantCard(restaurant: Restaurant) {
    Box(
        modifier = Modifier
            .size(160.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = restaurant.imageRes),
                contentDescription = "${restaurant.name} Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

// Mock Data Fetching Functions
fun fetchRestaurants(): List<Restaurant> = listOf(
    Restaurant("Joe's Grill", "123 Main St", 4.5, R.drawable.welcome_bg),
    Restaurant("Sushi Spot", "456 Elm St", 4.8, R.drawable.welcome_bg),
    Restaurant("Pizza Paradise", "789 Oak St", 4.3, R.drawable.welcome_bg),
    Restaurant("Pizza Paradise", "789 Oak St", 4.3, R.drawable.welcome_bg),
    Restaurant("Joe's Grill", "123 Main St", 4.5, R.drawable.welcome_bg),
    Restaurant("Sushi Spot", "456 Elm St", 4.8, R.drawable.welcome_bg),
    Restaurant("Pizza Paradise", "789 Oak St", 4.3, R.drawable.welcome_bg),
    Restaurant("Pizza Paradise", "789 Oak St", 4.3, R.drawable.welcome_bg),
    Restaurant("The Steakhouse", "987 Willow St", 4.9, R.drawable.welcome_bg),
    Restaurant("Gourmet Bistro", "654 Maple St", 4.7, R.drawable.welcome_bg),
    Restaurant("The Steakhouse", "987 Willow St", 4.9, R.drawable.welcome_bg),
    Restaurant("Gourmet Bistro", "654 Maple St", 4.7, R.drawable.welcome_bg),
    Restaurant("The Steakhouse", "987 Willow St", 4.9, R.drawable.welcome_bg),
)

fun fetchTopRatedRestaurants(): List<Restaurant> = listOf(
    Restaurant("The Steakhouse", "987 Willow St", 4.9, R.drawable.welcome_bg),
    Restaurant("Gourmet Bistro", "654 Maple St", 4.7, R.drawable.welcome_bg),
    Restaurant("The Steakhouse", "987 Willow St", 4.9, R.drawable.welcome_bg),
    Restaurant("Gourmet Bistro", "654 Maple St", 4.7, R.drawable.welcome_bg),
    Restaurant("The Steakhouse", "987 Willow St", 4.9, R.drawable.welcome_bg),
)

@Preview(showBackground = true)
@Composable
fun RestaurantsScreenPreview() {
    RestaurantsScreen()
}