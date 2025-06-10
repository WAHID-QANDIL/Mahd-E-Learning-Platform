package org.mahd_e_learning_platform.presentation.screens.welcome.create_account

data class CreateAccountUiState(
    val firstName: String = "",
    val lastName:String = "",
    val email: String = "",
    val password: String = "",
    val isRemembered: Boolean = false
)