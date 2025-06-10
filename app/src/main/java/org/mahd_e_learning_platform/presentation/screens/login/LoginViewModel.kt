package org.mahd_e_learning_platform.presentation.screens.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update


class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState


    fun onEmailTextChanged(value: String) {
        _uiState.update{
            it.copy(
                email = value
            )
        }
    }
    fun onPasswordTextChanged(value: String) {
        _uiState.update{
            it.copy(
                password = value
            )
        }
    }
    fun isChecked(value: Boolean) {
        _uiState.update{
            it.copy(
                isRemembered = value
            )
        }
    }



}