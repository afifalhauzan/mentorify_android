package com.example.tugas_3_avatar

import FaceAvatarScreen
import LoginScreen
import ProfileScreen
import RegistrationScreen
import UserViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tugas_3_avatar.ui.theme.Tugas_3_avatarTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas_3_avatarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            // Teruskan ViewModel ke LoginScreen
            LoginScreen(navController = navController, viewModel = userViewModel)
        }
        composable("registration") {
            // Teruskan ViewModel ke RegistrationScreen
            RegistrationScreen(navController = navController, viewModel = userViewModel)
        }
        composable("profile") {
            // Teruskan ViewModel ke ProfileScreen
            ProfileScreen(navController = navController, viewModel = userViewModel)
        }
        composable("avatar") {
            FaceAvatarScreen(navController = navController)
        }
    }
}