package org.mahd_e_learning_platform.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mahd_e_learning_platform.data.source.local.datastore.SecureTokenStore
import org.mahd_e_learning_platform.domain.model.Student
import org.mahd_e_learning_platform.domain.usecase.ProfileUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    profileUseCases: ProfileUseCases,
    secureTokenStore: SecureTokenStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            val student = profileUseCases.getUserProfile()
            _uiState.update {
                it.copy(
                    student = Student(
                        firstName = student.firstName,
                        lastname = student.lastName,
                        email = student.email,
                        role = student.role
                    )
                )
            }
        }
        viewModelScope.launch {
            secureTokenStore.saveLoginState(true)
        }
    }


}