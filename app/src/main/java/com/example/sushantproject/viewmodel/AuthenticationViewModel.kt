package com.example.sushantproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sushantproject.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> =
        _errorMessage

    // LOGIN
    fun login(
        email: String,
        pass: String,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            _isLoading.value = true

            try {

                repository.signIn(
                    email,
                    pass
                )

                onResult(true)

            } catch (e: Exception) {

                _errorMessage.value =
                    "Invalid email or password"

                onResult(false)

            } finally {

                _isLoading.value = false
            }
        }
    }

    // REGISTER
    fun register(
        name: String,
        email: String,
        phone: String,
        bloodGroup: String,
        pass: String,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            _isLoading.value = true

            try {

                repository.signUp(
                    email,
                    pass
                )

                onResult(true)

            } catch (e: Exception) {

                _errorMessage.value =
                    "Registration failed"

                onResult(false)

            } finally {

                _isLoading.value = false
            }
        }
    }
}