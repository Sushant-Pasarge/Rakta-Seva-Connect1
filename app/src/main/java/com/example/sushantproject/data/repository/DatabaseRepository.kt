package com.example.sushantproject.data.repository

import com.example.sushantproject.data.models.BloodRequest
import com.example.sushantproject.services.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order

class DatabaseRepository {
    private val postgrest = SupabaseClient.client.postgrest

    suspend fun getBloodRequests(): List<BloodRequest> {
        return postgrest.from("blood_requests")
            .select() {
                order("created_at", order = Order.DESCENDING)
            }
            .decodeList<BloodRequest>()
    }

    suspend fun createBloodRequest(request: BloodRequest) {
        postgrest.from("blood_requests").insert(request)
    }

    suspend fun getNearbyRequests(location: String): List<BloodRequest> {
        return postgrest.from("blood_requests")
            .select {
                filter {
                    eq("location", location)
                }
            }
            .decodeList<BloodRequest>()
    }
}
