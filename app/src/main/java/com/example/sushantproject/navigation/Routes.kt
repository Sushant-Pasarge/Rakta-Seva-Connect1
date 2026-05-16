package com.example.sushantproject.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object DonorMap : Screen("donor_map")
    object CreateRequest : Screen("create_request")
    object Notifications : Screen("notifications")
    object Profile : Screen("profile")
}
