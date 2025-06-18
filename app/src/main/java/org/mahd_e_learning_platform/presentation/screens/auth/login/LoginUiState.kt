package org.mahd_e_learning_platform.presentation.screens.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isRemembered: Boolean = false
)