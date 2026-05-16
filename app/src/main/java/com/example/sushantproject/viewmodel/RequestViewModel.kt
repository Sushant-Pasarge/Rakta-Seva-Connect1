package com.example.sushantproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sushantproject.data.models.BloodRequest
import com.example.sushantproject.data.repository.RequestRepository
import com.example.sushantproject.services.SupabaseClient
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RequestViewModel(
    private val repository: RequestRepository = RequestRepository()
) : ViewModel() {

    companion object {
        private const val TAG = "RequestViewModel"
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // ALL REQUESTS
    private val _allRequests =
        MutableStateFlow<List<BloodRequest>>(emptyList())

    val allRequests: StateFlow<List<BloodRequest>> =
        _allRequests

    // FILTERED REQUESTS
    private val _filteredRequests =
        MutableStateFlow<List<BloodRequest>>(emptyList())

    val filteredRequests: StateFlow<List<BloodRequest>> =
        _filteredRequests

    fun submitRequest(
        hospitalName: String,
        bloodGroup: String,
        units: Int,
        urgency: String,
        notes: String
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null

            try {

                Log.d(TAG, "Starting request submission...")

                val userId =
                    SupabaseClient.client.auth
                        .currentUserOrNull()?.id

                val request = BloodRequest(
                    hospital_name = hospitalName,
                    blood_group = bloodGroup,
                    units_required = units,
                    urgency_level = urgency,
                    notes = notes,
                    user_id = userId
                )

                repository.createRequest(request)

                Log.d(TAG, "Request submitted successfully")

                _isSuccess.value = true

                // RELOAD REQUESTS
                loadRequests()

            } catch (e: Exception) {

                Log.e(TAG, "Submission failed: ${e.message}")

                _errorMessage.value = e.message

                _isSuccess.value = false

            } finally {

                _isLoading.value = false
            }
        }
    }

    fun loadRequests() {

        viewModelScope.launch {

            try {

                val requests =
                    repository.getAllRequests()

                _allRequests.value = requests

                _filteredRequests.value = requests

                Log.d(TAG, "Requests loaded: ${requests.size}")

            } catch (e: Exception) {

                Log.e(TAG, "Load failed: ${e.message}")
            }
        }
    }

    fun filterRequests(bloodGroup: String) {

        if (bloodGroup == "All") {

            _filteredRequests.value =
                _allRequests.value

        } else {

            _filteredRequests.value =
                _allRequests.value.filter {

                    it.blood_group.trim()
                        .equals(
                            bloodGroup.trim(),
                            ignoreCase = true
                        )
                }
        }

        Log.d(
            TAG,
            "Filtered Requests: ${_filteredRequests.value.size}"
        )
    }

    fun resetState() {

        _isSuccess.value = false

        _errorMessage.value = null
    }
}