package org.mahd_e_learning_platform.presentation.screens.auth.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VerificationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VerificationUiState())
    val uiState = _uiState

    private var timerJob: Job? = null

    init {
        startTimer()
    }

    private fun startTimer() {
        // Cancel any existing timer
        timerJob?.cancel()
        // Start a new timer
        timerJob = viewModelScope.launch {
            _uiState.update { it.copy(isResendEnabled = false, remainingSeconds = 120) }
            for (i in 120 downTo 1) {
                delay(1000)
                _uiState.update { it.copy(remainingSeconds = i) }
            }
            _uiState.update { it.copy(isResendEnabled = true) }
        }
    }

    fun onOtpCodeChanged(code: String) {
        if (code.length <= 6) {
            _uiState.update {
                it.copy(otpCode = code)
            }
        }
    }

    fun onResendCode() {
        //TODO: Add logic to request a new code from the server
        startTimer()
    }

    fun onVerifyCode() {
        //TODO: Add logic to verify the OTP code
    }

    override fun onCleared() {
        super.onCleared()
        // Ensure the coroutine is cancelled when the ViewModel is destroyed
        timerJob?.cancel()
    }
}