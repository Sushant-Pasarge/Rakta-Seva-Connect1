package com.example.sushantproject.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sushantproject.data.models.Donor
import com.example.sushantproject.ui.theme.EmergencyRed
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DonorMapScreen() {

    val locationPermissionsState =
        rememberMultiplePermissionsState(

            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    if (locationPermissionsState.allPermissionsGranted) {

        MapContent()

    } else {

        Box(

            modifier = Modifier.fillMaxSize(),

            contentAlignment = Alignment.Center
        ) {

            Column(

                horizontalAlignment =
                    Alignment.CenterHorizontally,

                modifier =
                    Modifier.padding(16.dp)
            ) {

                Text(

                    text =
                        "Location access is required to find nearby donors.",

                    style =
                        MaterialTheme.typography.bodyLarge,

                    modifier =
                        Modifier.padding(bottom = 16.dp)
                )

                Button(
                    onClick = {
                        locationPermissionsState
                            .launchMultiplePermissionRequest()
                    }
                ) {

                    Text("Grant Permissions")
                }
            }
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun MapContent() {

    // INDIA CENTER LOCATION
    val indiaLocation =
        LatLng(20.5937, 78.9629)

    val cameraPositionState =
        rememberCameraPositionState {

            position =
                CameraPosition.fromLatLngZoom(
                    indiaLocation,
                    4.5f
                )
        }

    // MOCK DONOR DATA IN INDIA
    val nearbyDonors = remember {

        listOf(

            Donor(
                "1",
                "Sushant",
                "O+",
                18.5204,
                73.8567 // Pune
            ),

            Donor(
                "2",
                "Aman",
                "A-",
                19.0760,
                72.8777 // Mumbai
            ),

            Donor(
                "3",
                "Rahul",
                "B+",
                12.9716,
                77.5946 // Bangalore
            )
        )
    }

    val uiSettings by remember {

        mutableStateOf(

            MapUiSettings(
                myLocationButtonEnabled = true
            )
        )
    }

    val properties by remember {

        mutableStateOf(

            MapProperties(
                isMyLocationEnabled = true
            )
        )
    }

    Scaffold { padding ->

        GoogleMap(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding),

            cameraPositionState =
                cameraPositionState,

            uiSettings =
                uiSettings,

            properties =
                properties
        ) {

            // DONOR MARKERS
            nearbyDonors.forEach { donor ->

                Marker(

                    state = MarkerState(

                        position = LatLng(
                            donor.latitude,
                            donor.longitude
                        )
                    ),

                    title = donor.name,

                    snippet =
                        "Blood Group: ${donor.blood_group}"
                )
            }

            // INDIA AREA CIRCLE
            Circle(

                center = indiaLocation,

                radius = 500000.0,

                fillColor =
                    EmergencyRed.copy(alpha = 0.15f),

                strokeColor =
                    EmergencyRed,

                strokeWidth = 2f
            )
        }
    }
}