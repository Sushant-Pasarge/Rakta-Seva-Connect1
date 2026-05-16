package com.example.sushantproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sushantproject.navigation.Screen
import com.example.sushantproject.navigation.SetupNavGraph
import com.example.sushantproject.services.NotificationHelper
import com.example.sushantproject.ui.components.BottomNavigationBar
import com.example.sushantproject.ui.theme.RaktaSevaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaktaSevaTheme {
                LaunchedEffect(Unit) {
                    NotificationHelper.requestPermission()
                }
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    
    // Define screens where the bottom navigation bar should be visible
    val bottomBarScreens = listOf(
        Screen.Home.route,
        Screen.DonorMap.route,
        Screen.CreateRequest.route,
        Screen.Notifications.route,
        Screen.Profile.route
    )
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in bottomBarScreens) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SetupNavGraph(navController = navController)
        }
    }
}
