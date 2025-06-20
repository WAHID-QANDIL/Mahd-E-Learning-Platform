package org.mahd_e_learning_platform.presentation.screens.auth.forgot_password

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailTextChanged(value: String) {
        _uiState.update{
            it.copy(
                email = value
            )
        }
    }

    fun onSendResetLink() {
        //TODO: Handle password reset link logic
    }


    fun onContactSupport() {
        //TODO: Handle navigation back to login
    }
}