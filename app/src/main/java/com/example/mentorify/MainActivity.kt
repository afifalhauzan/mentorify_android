package com.example.mentorify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.mentorify.navigation.AppNavigation
import com.example.mentorify.navigation.Routes
import com.example.mentorify.ui.theme.MentorifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MentorifyTheme {
                val navController = rememberNavController()
                // For now, always start with onboarding. Later, you can add logic
                // to check if onboarding has been completed (e.g., using DataStore or SharedPreferences)
                AppNavigation(navController = navController, startDestination = Routes.Onboarding)
            }
        }
    }
}
