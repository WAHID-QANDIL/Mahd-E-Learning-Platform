package org.mahd_e_learning_platform.presentation.screens.auth.create_account

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateAccountViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccountUiState())
    val uiState = _uiState.asStateFlow()




    fun onEmailTextChanged(value: String) {
        _uiState.update{
            it.copy(
                email = value
            )
        }
    }


    fun onFirstNameChange(value: String) {
        _uiState.update{
            it.copy(
                firstName = value
            )
        }
    }
    fun onLastNameChange(value: String) {
        _uiState.update{
            it.copy(
                lastName = value
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
    fun onCreateAccount(){
        //TODO
    }


}