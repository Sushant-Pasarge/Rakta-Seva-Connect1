package com.example.sushantproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class AlertItem(
    val hospital: String,
    val bloodGroup: String,
    val urgency: String,
    val location: String
)

@Composable
fun NotificationsScreen() {

    var showHelpDialog by remember {
        mutableStateOf(false)
    }

    val alerts = listOf(

        AlertItem(
            "City Hospital",
            "O+",
            "Critical",
            "Downtown"
        ),

        AlertItem(
            "Red Cross",
            "B-",
            "Urgent",
            "North Side"
        ),

        AlertItem(
            "General Clinic",
            "A+",
            "Standard",
            "West End"
        )
    )

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        items(alerts) { alert ->

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row {

                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = alert.hospital,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Blood Group: ${alert.bloodGroup}"
                    )

                    Text(
                        text = "Urgency: ${alert.urgency}"
                    )

                    Text(
                        text = "Location: ${alert.location}"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {
                            showHelpDialog = true
                        }
                    ) {

                        Text("HELP")
                    }
                }
            }
        }
    }

    // HELP DIALOG
    if (showHelpDialog) {

        AlertDialog(

            onDismissRequest = {
                showHelpDialog = false
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        showHelpDialog = false
                    }
                ) {

                    Text("Accept")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showHelpDialog = false
                    }
                ) {

                    Text("Cancel")
                }
            },

            title = {
                Text("Emergency Blood Request")
            },

            text = {
                Text(
                    "Do you want to help this patient?"
                )
            }
        )
    }
}