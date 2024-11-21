package com.technopradyumn.fastfood.presentation.view.AllScreens

import android.content.Context
import android.location.Geocoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.fastfood.R

@Composable
fun DiscoverScreen() {
    var location by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf<List<String>>(emptyList()) }
    var bestSellers by remember { mutableStateOf<List<String>>(emptyList()) }
    var popularItems by remember { mutableStateOf<List<String>>(emptyList()) }

    val context = LocalContext.current

    LaunchedEffect(true) {
        location = getCurrentLocation(context)
        categories = fetchCategories()
        bestSellers = fetchBestSellers()
        popularItems = fetchPopularItems()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        // Header Row with Location and Logo
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = Color(0xFFFFA500),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Logo",
                        modifier = Modifier.size(16.dp),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Home,",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = location,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = { /* Handle Menu Click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_bottom),
                        contentDescription = "Menu",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Carousel Section
        item {
            val pagerState = rememberPagerState { 5 }
            Box(modifier = Modifier.fillMaxWidth()) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) { page ->
                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = "Carousel Image $page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Categories Section
        item {
            SectionHeader(title = "Categories")
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryItem(category)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Best Sellers Section
        item {
            SectionHeader(title = "Best Seller Food", showButton = true)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(bestSellers) { item ->
                    FoodItemCard(item)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Popular Items Section
        item {
            SectionHeader(title = "Popular Items", showButton = true)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(popularItems) { item ->
                    FoodItemCard(item)
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


@Composable
fun SectionHeader(title: String, showButton: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black
        )
        if (showButton) {
            FilledTonalButton(
                onClick = { /* Handle View All */ },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(0xFFFFA500).copy(0.2f), // Orange background
                    contentColor = Color(0xFFFFA500) // White text
                ),
                modifier = Modifier
                    .height(32.dp)
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(text = "See All")
            }

        }
    }
}

@Composable
fun CategoryItem(category: String) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.LightGray, Color.White)
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}

@Composable
fun FoodItemCard(item: String) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Food Image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = item,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$12.99",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFFFFA500)
                )
                IconButton(onClick = { /* Add to Cart */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.welcome_bg),
                        contentDescription = "Add to Cart",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

// Helper functions for location and mock data
fun getCurrentLocation(context: Context): String {
    val geocoder = Geocoder(context)
    val addresses = geocoder.getFromLocation(37.7749, -122.4194, 1)
    return addresses?.firstOrNull()?.getAddressLine(0) ?: "Unknown Location"
}

fun fetchCategories(): List<String> {
    return listOf("Pizza", "Burger", "Pasta", "Salads", "Desserts")
}

fun fetchBestSellers(): List<String> {
    return listOf("Burger King", "Pizza Hut", "Domino's", "Subway", "KFC")
}

fun fetchPopularItems(): List<String> {
    return listOf("Tacos", "Fried Chicken", "Ice Cream", "Steak", "Sushi")
}

@Preview(showBackground = true)
@Composable
fun DiscoverScreenPreview() {
    DiscoverScreen()
}