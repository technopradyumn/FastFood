package com.technopradyumn.fastfood.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun DividerWithText(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Divider(
            color = Color.DarkGray,
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
        )
        Text(
            text = text,
            style = TextStyle(color = Color.DarkGray),
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            color = Color.DarkGray,
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
        )
    }
}