package com.example.sushantproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BloodRequestItem(
    val id: Int,
    val name: String,
    val bloodGroup: String,
    val location: String,
    val urgency: String,
    val distance: String
)

@Composable
fun HomeScreen(
    onCreateRequestClick: () -> Unit
) {

    val bloodGroups = listOf(
        "A+",
        "A-",
        "B+",
        "B-",
        "AB+",
        "AB-",
        "O+",
        "O-",
        "Bombay Blood Group (hh)",
        "Rh-null",
        "A1+",
        "A1-",
        "A2+",
        "A2-",
        "A1B+",
        "A1B-",
        "A2B+",
        "A2B-"
    )

    var selectedGroup by remember {
        mutableStateOf("All")
    }

    // ALL REQUESTS
    val allRequests = listOf(

        BloodRequestItem(
            1,
            "City Hospital",
            "O+",
            "Downtown",
            "Critical",
            "1.2 km"
        ),

        BloodRequestItem(
            2,
            "Red Cross",
            "B-",
            "North Side",
            "Urgent",
            "3.5 km"
        ),

        BloodRequestItem(
            3,
            "General Clinic",
            "A+",
            "West End",
            "Standard",
            "5.0 km"
        ),

        BloodRequestItem(
            4,
            "St. Mary's",
            "AB-",
            "East Side",
            "Critical",
            "0.8 km"
        )
    )

    // FILTER LOGIC
    val filteredRequests = if (selectedGroup == "All") {

        allRequests

    } else {

        allRequests.filter {

            it.bloodGroup.trim()
                .equals(
                    selectedGroup.trim(),
                    ignoreCase = true
                )
        }
    }

    Scaffold(

        floatingActionButton = {

            ExtendedFloatingActionButton(

                onClick = onCreateRequestClick,

                containerColor =
                    MaterialTheme.colorScheme.primary,

                contentColor =
                    MaterialTheme.colorScheme.onPrimary,

                icon = {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null
                    )
                },

                text = {
                    Text("REQUEST BLOOD")
                }
            )
        }

    ) { paddingValues ->

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),

            contentPadding = PaddingValues(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)

        ) {

            // HEADER
            item {

                Text(

                    text = "Hello, Lifesaver!",

                    style =
                        MaterialTheme.typography.displaySmall,

                    fontWeight = FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onSurface
                )
            }

            // EMERGENCY BANNER
            item {
                EmergencyBanner()
            }

            // FILTER SECTION
            item {

                Column {

                    Text(

                        text = "Filter by Blood Group",

                        style =
                            MaterialTheme.typography.titleMedium,

                        fontWeight = FontWeight.SemiBold,

                        modifier =
                            Modifier.padding(bottom = 8.dp)
                    )

                    LazyRow(
                        horizontalArrangement =
                            Arrangement.spacedBy(8.dp)
                    ) {

                        // Add "All" manually for filtering purposes while keeping the updated list
                        val filterList = listOf("All") + bloodGroups

                        items(filterList) { group ->

                            FilterChip(

                                selected =
                                    selectedGroup == group,

                                onClick = {
                                    selectedGroup = group
                                },

                                label = {
                                    Text(group)
                                },

                                colors =
                                    FilterChipDefaults
                                        .filterChipColors(

                                            selectedContainerColor =
                                                MaterialTheme.colorScheme.primary,

                                            selectedLabelColor =
                                                MaterialTheme.colorScheme.onPrimary
                                        )
                            )
                        }
                    }
                }
            }

            // SECTION TITLE
            item {

                Text(

                    text = "Nearby Requests",

                    style =
                        MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold
                )
            }

            // FILTERED REQUESTS
            items(filteredRequests) { request ->

                BloodRequestCard(request)
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(80.dp)
                )
            }
        }
    }
}

@Composable
fun EmergencyBanner() {

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    MaterialTheme.colorScheme.errorContainer
            ),

        shape = RoundedCornerShape(16.dp)
    ) {

        Row(

            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Icon(

                Icons.Default.Warning,

                contentDescription = null,

                tint =
                    MaterialTheme.colorScheme.error,

                modifier =
                    Modifier.size(40.dp)
            )

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column {

                Text(

                    text = "Critical Emergency Nearby",

                    style =
                        MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onErrorContainer
                )

                Text(

                    text = "A- negative needed at City Hospital.",

                    style =
                        MaterialTheme.typography.bodyMedium,

                    color =
                        MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}

@Composable
fun BloodRequestCard(
    request: BloodRequestItem
) {

    var showHelpDialog by remember {
        mutableStateOf(false)
    }

    ElevatedCard(

        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(16.dp)
    ) {

        Row(

            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            // BLOOD GROUP CIRCLE
            Box(

                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(

                    text = request.bloodGroup,

                    style =
                        MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(

                    text = request.name,

                    style =
                        MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(

                        Icons.Default.LocationOn,

                        contentDescription = null,

                        modifier =
                            Modifier.size(14.dp),

                        tint =
                            MaterialTheme.colorScheme.secondary
                    )

                    Text(

                        text =
                            "${request.location} • ${request.distance}",

                        style =
                            MaterialTheme.typography.bodySmall,

                        color =
                            MaterialTheme.colorScheme.secondary
                    )
                }

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                SuggestionChip(

                    onClick = { },

                    label = {

                        Text(

                            text = request.urgency,

                            fontSize = 10.sp
                        )
                    },

                    colors =
                        SuggestionChipDefaults
                            .suggestionChipColors(

                                containerColor =
                                    if (request.urgency == "Critical")

                                        MaterialTheme.colorScheme.errorContainer

                                    else

                                        MaterialTheme.colorScheme.surfaceVariant
                            )
                )
            }

            Button(

                onClick = {
                    showHelpDialog = true
                },

                contentPadding =
                    PaddingValues(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    )
            ) {

                Text("HELP")
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
                Text("Blood Donation Request")
            },

            text = {
                Text(
                    "Do you want to help this patient?"
                )
            }
        )
    }
}
