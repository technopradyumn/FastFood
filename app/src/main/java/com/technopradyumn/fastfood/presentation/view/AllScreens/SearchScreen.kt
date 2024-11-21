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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import com.technopradyumn.fastfood.R
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    val allItems = fetchAllItems()
    val filteredItems = allItems
        .filter { it.name.contains(searchQuery, ignoreCase = true) }
        .sortedBy { it.name }  // Sorting alphabetically by item name

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        OutlinedTextFieldWithError(
            placeholderText = "Search for food or restaurant",
            value = searchQuery,
            onValueChange = { newQuery -> searchQuery = newQuery },
            label = "Search",
            isError = false,
            errorMessage = "Search query must be at least 3 characters"
        )

        Text(
            text = "Search Results",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredItems) { item ->
                SearchResultCard(item)
            }
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun OutlinedTextFieldWithError(
    placeholderText: String,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorMessage: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            singleLine = true,
            placeholder = { Text(text = placeholderText) },
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                errorBorderColor = Color.Red,
                unfocusedBorderColor = Color.LightGray,
                focusedBorderColor = Color(0xFFFF9800),
                disabledBorderColor = Color.Gray,
                unfocusedContainerColor = Color.Transparent
            )
        )

        if (isError) {
            Text(text = errorMessage, color = Color.Red, fontSize = 12.sp)
        }
    }
}

@Composable
fun SearchResultCard(item: Item) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "${item.name} Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape), // Circular image
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
                    text = item.location,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rating: ${item.rating}",
                    fontSize = 14.sp,
                    color = Color.Green
                )
            }
        }
    }
}

// Mock Data for Search Results
fun fetchAllItems(): List<Item> {
    val items = listOf(
        Item("Joe's Grill", "Restaurant", "123 Main St", 4.5, R.drawable.welcome_bg),
        Item("Sushi Spot", "Restaurant", "456 Elm St", 4.8, R.drawable.welcome_bg),
        Item("Pizza Paradise", "Restaurant", "789 Oak St", 4.3, R.drawable.welcome_bg),
        Item("Spicy Wings", "Food", "123 Main St", 4.6, R.drawable.welcome_bg),
        Item("Burger Delight", "Food", "456 Elm St", 4.2, R.drawable.welcome_bg),
        Item("Pasta World", "Food", "789 Oak St", 4.4, R.drawable.welcome_bg),
        Item("Joe's Grill", "Restaurant", "123 Main St", 4.5, R.drawable.welcome_bg),
        Item("Sushi Spot", "Restaurant", "456 Elm St", 4.8, R.drawable.welcome_bg),
        Item("Pizza Paradise", "Restaurant", "789 Oak St", 4.3, R.drawable.welcome_bg),
        Item("Spicy Wings", "Food", "123 Main St", 4.6, R.drawable.welcome_bg),
        Item("Burger Delight", "Food", "456 Elm St", 4.2, R.drawable.welcome_bg),
        Item("Pasta World", "Food", "789 Oak St", 4.4, R.drawable.welcome_bg)
    )

    // Shuffle the items randomly to create a random order
    return items.shuffled(Random(System.currentTimeMillis()))
}

data class Item(
    val name: String,
    val type: String, // Can be "Food" or "Restaurant"
    val location: String,
    val rating: Double,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}