package org.mahd_e_learning_platform.presentation.screens.profile.security_c_password

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SecurityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SecurityUiState())
    val uiState = _uiState.asStateFlow()

    fun onCurrentPasswordChanged(password: String) {
        _uiState.update { it.copy(currentPassword = password) }
    }

    fun onNewPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                newPassword = password,
                passwordRequirements = validatePassword(password),
                passwordsMatch = password == it.confirmNewPassword && password.isNotEmpty()
            )
        }
    }

    fun onConfirmNewPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                confirmNewPassword = password,
                passwordsMatch = it.newPassword == password && password.isNotEmpty()
            )
        }
    }

    private fun validatePassword(password: String): PasswordRequirements {
        return PasswordRequirements(
            hasMinLength = password.length >= 8,
            hasUppercase = password.any { it.isUpperCase() },
            hasNumber = password.any { it.isDigit() },
            hasSpecialChar = password.any { !it.isLetterOrDigit() }
        )
    }

    fun onUpdatePasswordClicked() {
        val currentState = _uiState.value
        if (currentState.passwordRequirements.allMet && currentState.passwordsMatch) {
            // TODO: Implement password update logic
        }
    }

    fun toggleVisibility(field: String) {
        _uiState.update {
            when (field) {
                "current" -> it.copy(isCurrentPasswordVisible = !it.isCurrentPasswordVisible)
                "new" -> it.copy(isNewPasswordVisible = !it.isNewPasswordVisible)
                "confirm" -> it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible)
                else -> it
            }
        }
    }

    fun onNavigateBack() {
        // TODO: Handle back navigation
    }
}