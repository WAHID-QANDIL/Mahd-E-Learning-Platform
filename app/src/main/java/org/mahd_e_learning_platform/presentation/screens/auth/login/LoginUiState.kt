package org.mahd_e_learning_platform.presentation.screens.auth.login

import org.mahd_e_learning_platform.domain.model.Student

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isRemembered: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

//sealed class LoginState {
//    data class Ui(
//        val uiState: LoginUiState,
//    ) : LoginState()
//    object Idle : LoginState()
//    object Loading : LoginState()
//    data class Success(val student: Student) : LoginState()
//    data class Error(val errorMessage: String) : LoginState()
//}