package com.technopradyumn.fastfood.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TextWithClickableAction(staticText: String, actionText: String, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = staticText, color = Color.Black)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = actionText,
            color = Color(0xFFFF9800),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}