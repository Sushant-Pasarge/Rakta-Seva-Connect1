package com.example.sushantproject.data.models

import kotlinx.serialization.Serializable

@Serializable
data class BloodRequest(

    val id: String? = null,

    val hospital_name: String,

    val blood_group: String,

    val units_required: Int,

    val urgency_level: String,

    val notes: String = "",

    val location: String = "",

    val created_at: String? = null,

    val user_id: String? = null
)