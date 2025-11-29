package com.example.mentorify.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mentorify.home.beranda.BerandaScreen
import com.example.mentorify.home.cari.CariScreen
import com.example.mentorify.home.berita.BeritaScreen
import com.example.mentorify.home.profil.ProfilScreen
import com.example.mentorify.navigation.Routes
import com.example.mentorify.ui.theme.MentorifyTheme

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Beranda : BottomNavItem(Routes.Beranda, Icons.Default.Home, "Beranda")
    object Cari : BottomNavItem(Routes.Cari, Icons.Default.Search, "Cari")
    object Berita : BottomNavItem(Routes.Berita, Icons.Default.Home, "Berita")
    object Profil : BottomNavItem(Routes.Profil, Icons.Default.Person, "Profil")
}

val bottomNavItems = listOf(
    BottomNavItem.Beranda,
    BottomNavItem.Cari,
    BottomNavItem.Berita,
    BottomNavItem.Profil,
)

@Composable
fun MainHomeScreen(navController: NavHostController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry = bottomNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry.value?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                bottomNavController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(bottomNavController, startDestination = BottomNavItem.Beranda.route, Modifier.padding(paddingValues)) {
            composable(Routes.Beranda) { BerandaScreen() }
            composable(Routes.Cari) { CariScreen() }
            composable(Routes.Berita) { BeritaScreen() }
            composable(Routes.Profil) { ProfilScreen() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainHomeScreenPreview() {
    MentorifyTheme {
        MainHomeScreen(navController = rememberNavController())
    }
}