package com.example.sushantproject.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Donor(
    val id: String,
    val name: String,
    val blood_group: String,
    val latitude: Double,
    val longitude: Double,
    val is_available: Boolean = true
)
