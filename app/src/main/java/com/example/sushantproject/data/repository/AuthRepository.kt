package com.example.sushantproject.data.repository

import android.util.Log
import com.example.sushantproject.services.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class AuthRepository {

    companion object {
        private const val TAG = "AuthRepository"
    }

    private val auth =
        SupabaseClient.client.auth

    // SIGN UP
    suspend fun signUp(
        email: String,
        pass: String
    ) {

        try {

            auth.signUpWith(Email) {

                this.email = email

                this.password = pass
            }

            Log.d(
                TAG,
                "Registration successful"
            )

        } catch (e: Exception) {

            Log.e(
                TAG,
                "Registration failed: ${e.message}"
            )

            throw e
        }
    }

    // SIGN IN
    suspend fun signIn(
        email: String,
        pass: String
    ) {

        try {

            auth.signInWith(Email) {

                this.email = email

                this.password = pass
            }

            Log.d(
                TAG,
                "Login successful"
            )

        } catch (e: Exception) {

            Log.e(
                TAG,
                "Login failed: ${e.message}"
            )

            throw e
        }
    }

    // SIGN OUT
    suspend fun signOut() {

        auth.signOut()
    }

    // CURRENT USER
    fun getCurrentUser() =
        auth.currentUserOrNull()
}