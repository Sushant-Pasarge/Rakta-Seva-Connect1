package com.example.sushantproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("Home", Screen.Home.route, Icons.Default.Home)
    object Map : BottomNavItem("Map", Screen.DonorMap.route, Icons.Default.LocationOn)
    object Request : BottomNavItem("Request", Screen.CreateRequest.route, Icons.Default.AddCircle)
    object Notifications : BottomNavItem("Alerts", Screen.Notifications.route, Icons.Default.Notifications)
    object Profile : BottomNavItem("Profile", Screen.Profile.route, Icons.Default.Person)
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Map,
    BottomNavItem.Request,
    BottomNavItem.Notifications,
    BottomNavItem.Profile
)
