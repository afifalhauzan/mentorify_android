package com.example.mentorify.home.cari

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mentorify.ui.theme.MentorifyTheme

@Composable
fun CariScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Cari Screen Content")
    }
}

@Preview(showBackground = true)
@Composable
fun CariScreenPreview() {
    MentorifyTheme {
        CariScreen()
    }
}