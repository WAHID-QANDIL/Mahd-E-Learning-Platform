package org.mahd_e_learning_platform.presentation.screens.auth.verification

data class VerificationUiState(
    val otpCode: String = "",
    val remainingSeconds: Int = 120, // 2 minutes
    val isResendEnabled: Boolean = false
)