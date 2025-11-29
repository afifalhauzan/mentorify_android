package com.example.mentorify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mentorify.onboarding.OnboardingScreen
import com.example.mentorify.auth.login.LoginScreen
import com.example.mentorify.auth.login.LoginViewModel
import com.example.mentorify.auth.register.RegisterScreen
import com.example.mentorify.auth.register.RegisterViewModel
import com.example.mentorify.home.MainHomeScreen // Import the new home screen

object Graph {
    const val ROOT = "root_graph"
    const val ONBOARDING = "onboarding_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
}

object Routes {
    const val Onboarding = "onboarding_route"
    const val Login = "login_route"
    const val Register = "register_route"
    const val Home = "home_route"
    // Bottom Navigation Routes
    const val Beranda = "beranda_route"
    const val Cari = "cari_route"
    const val Berita = "berita_route"
    const val Profil = "profil_route"
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = Graph.ROOT
    ) {
        composable(Routes.Onboarding) {
            OnboardingScreen(navController = navController) {
                // Onboarding finished, navigate to Login
                navController.popBackStack()
                navController.navigate(Routes.Login)
            }
        }

        composable(Routes.Login) {
            val loginViewModel = androidx.lifecycle.viewmodel.compose.viewModel<LoginViewModel>()
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel,
                onLoginSuccess = { navController.navigate(Routes.Home) { popUpTo(Graph.ROOT) { inclusive = true } } },
                onNavigateToRegister = { navController.navigate(Routes.Register) },
                onNavigateToForgotPassword = { /* TODO: Implement Forgot Password navigation */ }
            )
        }

        composable(Routes.Register) {
            val registerViewModel = androidx.lifecycle.viewmodel.compose.viewModel<RegisterViewModel>()
            RegisterScreen(
                navController = navController,
                viewModel = registerViewModel,
                onRegisterSuccess = { navController.navigate(Routes.Login) { popUpTo(Routes.Login) { inclusive = true } } },
                onNavigateToLogin = { navController.navigate(Routes.Login) { popUpTo(Routes.Login) { inclusive = true } } },
                onBack = { navController.popBackStack() }
            )
        }

        // The main Home screen with bottom navigation
        composable(Routes.Home) {
            MainHomeScreen(navController = navController)
        }
    }
}
