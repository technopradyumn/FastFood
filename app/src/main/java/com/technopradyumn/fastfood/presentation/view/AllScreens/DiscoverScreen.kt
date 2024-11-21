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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.fastfood.R
import com.technopradyumn.fastfood.data.model.FoodItem
import com.technopradyumn.fastfood.utils.SectionHeader

@Composable
fun DiscoverScreen() {
    var location by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf(emptyList<FoodItem>()) }
    var bestSellers by remember { mutableStateOf(emptyList<FoodItem>()) }
    var popularItems by remember { mutableStateOf(emptyList<FoodItem>()) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
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
        // Header Section
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(color = Color(0xFFFFA500), shape = CircleShape),
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Home,",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = location,
                        fontSize = 8.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_bottom),
                            contentDescription = "Menu",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Carousel Section
        item {
            val imageResources = listOf(
                R.drawable.welcome_bg,
                R.drawable.welcome_bg,
                R.drawable.welcome_bg
            )

            val pagerState = rememberPagerState(initialPage = 0) {
                imageResources.size
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(16.dp))
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    Image(
                        painter = painterResource(id = imageResources[page]),
                        contentDescription = "Carousel Image ${page + 1}",
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
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = category.imageRes),
                                contentDescription = "${category.name} Image",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.White),
                                contentScale = ContentScale.Crop,
                            )
                            Text(
                                text = category.name,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Best Sellers Section
        item {
            SectionHeader(title = "Best Seller Food")
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(bestSellers) { item ->
                    FoodCard(item)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Popular Items Section
        item {
            SectionHeader(title = "Popular Items")
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(popularItems) { item ->
                    FoodCard(item)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item{
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Composable
fun FoodCard(item: FoodItem) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "${item.name} Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )
            Text(
                text = item.name,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

// Helper Functions
fun getCurrentLocation(context: Context): String {
    val geocoder = Geocoder(context)
    val addresses = geocoder.getFromLocation(37.7749, -122.4194, 1)
    return addresses?.firstOrNull()?.getAddressLine(0) ?: "Unknown Location"
}

fun fetchCategories(): List<FoodItem> = listOf(
    FoodItem(name = "Pizza", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Burger", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Pasta", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Burger", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Pasta", imageRes = R.drawable.welcome_bg)
)

fun fetchBestSellers(): List<FoodItem> = listOf(
    FoodItem(name = "Burger King", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Pizza Hut", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Burger King", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Pizza Hut", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Burger King", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Pizza Hut", imageRes = R.drawable.welcome_bg)
)

fun fetchPopularItems(): List<FoodItem> = listOf(
    FoodItem(name = "Tacos", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Fried Chicken", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Tacos", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Fried Chicken", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Tacos", imageRes = R.drawable.welcome_bg),
    FoodItem(name = "Fried Chicken", imageRes = R.drawable.welcome_bg)
)

@Preview(showBackground = true)
@Composable
fun DiscoverScreenPreview() {
    DiscoverScreen()
}