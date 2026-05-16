package com.example.sushantproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sushantproject.services.SupabaseClient
import io.github.jan.supabase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogoutClick: () -> Unit
) {

    var available by remember {
        mutableStateOf(true)
    }

    // CURRENT USER
    val currentUser =
        SupabaseClient.client.auth.currentUserOrNull()

    val email =
        currentUser?.email ?: "No Email"

    val userName =
        currentUser?.email
            ?.substringBefore("@")
            ?: "User"

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("My Profile")
                },

                colors =
                    TopAppBarDefaults
                        .topAppBarColors(

                            containerColor =
                                MaterialTheme.colorScheme.primary,

                            titleContentColor =
                                MaterialTheme.colorScheme.onPrimary
                        )
            )
        }

    ) { padding ->

        Surface(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            color =
                MaterialTheme.colorScheme.background
        ) {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                // PROFILE IMAGE
                Surface(

                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),

                    color =
                        MaterialTheme.colorScheme.primaryContainer
                ) {

                    Box(
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(

                            Icons.Default.Person,

                            contentDescription = null,

                            modifier =
                                Modifier.size(60.dp),

                            tint =
                                MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // USER NAME
                Text(

                    text = userName,

                    style =
                        MaterialTheme.typography.headlineMedium,

                    fontWeight = FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onBackground
                )

                Text(

                    text = "Emergency Blood Donor",

                    style =
                        MaterialTheme.typography.bodyLarge,

                    color =
                        MaterialTheme.colorScheme.secondary
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                // EMAIL CARD
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(

                        modifier =
                            Modifier.padding(16.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(

                            Icons.Default.Email,

                            contentDescription = null,

                            tint =
                                MaterialTheme.colorScheme.primary
                        )

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )

                        Text(
                            email,
                            color =
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // PHONE CARD
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(

                        modifier =
                            Modifier.padding(16.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(

                            Icons.Default.Phone,

                            contentDescription = null,

                            tint =
                                MaterialTheme.colorScheme.primary
                        )

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )

                        Text(
                            "+91 98765 43210",
                            color =
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // AVAILABILITY SWITCH
                Row(

                    modifier = Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(

                        text =
                            "Available for Emergency Donation",

                        style =
                            MaterialTheme.typography.titleMedium,

                        color =
                            MaterialTheme.colorScheme.onBackground
                    )

                    Switch(

                        checked = available,

                        onCheckedChange = {
                            available = it
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                // LOGOUT BUTTON
                Button(

                    onClick = onLogoutClick,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),

                    colors =
                        ButtonDefaults.buttonColors(

                            containerColor =
                                MaterialTheme.colorScheme.error
                        )
                ) {

                    Icon(

                        Icons.AutoMirrored.Filled.ExitToApp,

                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Text(
                        "Logout",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}