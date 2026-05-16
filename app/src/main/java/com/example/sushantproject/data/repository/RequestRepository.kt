package com.example.sushantproject.data.repository

import android.util.Log
import com.example.sushantproject.data.models.BloodRequest
import com.example.sushantproject.services.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class RequestRepository {

    companion object {
        private const val TAG = "RequestRepository"
    }

    suspend fun createRequest(request: BloodRequest) {

        try {

            Log.d(TAG, "Inserting request into Supabase...")

            SupabaseClient.client
                .from("blood_requests")
                .insert(request)

            Log.d(TAG, "Insert successful")

        } catch (e: Exception) {

            Log.e(TAG, "Insert failed: ${e.message}")

            throw e
        }
    }

    suspend fun getAllRequests(): List<BloodRequest> {

        return try {

            SupabaseClient.client
                .from("blood_requests")
                .select()
                .decodeList<BloodRequest>()

        } catch (e: Exception) {

            Log.e(TAG, "Fetch failed: ${e.message}")

            emptyList()
        }
    }
}