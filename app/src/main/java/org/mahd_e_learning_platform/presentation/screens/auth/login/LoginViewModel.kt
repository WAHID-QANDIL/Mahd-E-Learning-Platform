package org.mahd_e_learning_platform.presentation.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mahd_e_learning_platform.domain.usecase.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()


    fun onEmailTextChanged(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
    }

    fun onPasswordTextChanged(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    fun isChecked(value: Boolean) {
        _uiState.update {
            it.copy(
                isRemembered = value
            )
        }
    }

    fun onSignIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                authUseCases.loginUseCase(email = email, password = password)
                _uiState.update {
                    it.copy(
                        isSuccess = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.toString()
                    )
                }
                Log.d("onSignIn", "onSignIn: $e")

            }

        }
    }


}