package com.technopradyumn.fastfood.presentation.view.AllScreens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen() {
    Scaffold(
        content = {
            Text("Search")
        }
    )
}