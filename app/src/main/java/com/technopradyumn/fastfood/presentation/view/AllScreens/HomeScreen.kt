package com.technopradyumn.fastfood.presentation.view.AllScreens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.fastfood.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navItemList = listOf(
        BottomNavItem("Discover", R.drawable.discover_ic),
        BottomNavItem("Restaurants", R.drawable.store),
        BottomNavItem("Search", R.drawable.search),
        BottomNavItem("Favorite", R.drawable.heart),
        BottomNavItem("Profile", R.drawable.profile_ic)
    )

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    BackHandler(enabled = selectedIndex != 0) {
        selectedIndex = 0
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ContentArea(selectedIndex = selectedIndex)

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ){
            Box(){
                BottomNavigationBar(
                    navItemList = navItemList,
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it }
                )
            }

        }

    }
}

@Composable
fun ContentArea(selectedIndex: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (selectedIndex) {
            0 -> DiscoverScreen()
            1 -> RestaurantsScreen()
            2 -> SearchScreen()
            3 -> FavoriteScreen()
            4 -> ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    navItemList: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
            .padding(16.dp)
            .background(
                color = Color(0xFFFFE0B2).copy(alpha = 0.9f),
                shape = RoundedCornerShape(24.dp)
            )
            .blur(16.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItemList.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .noRippleClickable { onItemSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name,
                            tint = if (selectedIndex == index) Color(0xFFFFA500) else Color.DarkGray
                        )
                        Text(
                            text = item.name,
                            fontSize = 10.sp,
                            color = if (selectedIndex == index) Color(0xFFFFA500) else Color.Gray
                        )
                    }
                }
            }
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = this.clickable(
    indication = null,
    interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource()
) { onClick() }

data class BottomNavItem(
    val name: String,
    val icon: Int
)

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}