package org.mahd_e_learning_platform.presentation.screens.auth.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mahd_e_learning_platform.data.api.model.RegisterRequest
import org.mahd_e_learning_platform.domain.usecase.AuthUseCases
import javax.inject.Inject
@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccountUiState())
    val uiState = _uiState.asStateFlow()


    fun onEmailTextChanged(value: String) {
        _uiState.update {
            it.copy(
                email = value
            )
        }
    }


    fun onFirstNameChange(value: String) {
        _uiState.update {
            it.copy(
                firstName = value
            )
        }
    }

    fun onLastNameChange(value: String) {
        _uiState.update {
            it.copy(
                lastName = value
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

    fun onCreateAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        role: String = "student",
    ) {
        viewModelScope.launch {
            authUseCases.registerUseCase(
                registerRequest = RegisterRequest(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    role = role,
                )
            )
        }

    }


}