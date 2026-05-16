package com.example.sushantproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {

    LaunchedEffect(Unit) {

        delay(2000)

        onTimeout()
    }

    Box(

        modifier = Modifier.fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {

        Column(

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Icon(

                imageVector =
                    Icons.Default.Favorite,

                contentDescription = null,

                tint =
                    MaterialTheme.colorScheme.primary,

                modifier =
                    Modifier.size(100.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(

                text = "RaktaSeva Connect",

                style =
                    MaterialTheme.typography.displayLarge,

                color =
                    MaterialTheme.colorScheme.primary
            )
        }
    }
}