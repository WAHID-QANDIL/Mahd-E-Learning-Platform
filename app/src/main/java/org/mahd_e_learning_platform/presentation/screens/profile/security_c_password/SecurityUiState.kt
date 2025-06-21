package org.mahd_e_learning_platform.presentation.screens.profile.security_c_password

// Represents the validation status of the new password
data class PasswordRequirements(
    val hasMinLength: Boolean = false,
    val hasUppercase: Boolean = false,
    val hasNumber: Boolean = false,
    val hasSpecialChar: Boolean = false
) {
    // Calculated property to check if all requirements are met
    val allMet: Boolean
        get() = hasMinLength && hasUppercase && hasNumber && hasSpecialChar
}

// The main state holder for the Security screen
data class SecurityUiState(
    val currentPassword: String = "",
    val newPassword: String = "",
    val confirmNewPassword: String = "",
    val isCurrentPasswordVisible: Boolean = false,
    val isNewPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val passwordRequirements: PasswordRequirements = PasswordRequirements(),
    val passwordsMatch: Boolean = false
)