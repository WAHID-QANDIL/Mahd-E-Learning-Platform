package org.mahd_e_learning_platform.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mahd_e_learning_platform.data.source.local.datastore.SecureTokenStore
import org.mahd_e_learning_platform.domain.model.Student
import org.mahd_e_learning_platform.domain.usecase.GetRecommendedCourses
import org.mahd_e_learning_platform.domain.usecase.ProfileUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    profileUseCases: ProfileUseCases,
    secureTokenStore: SecureTokenStore,
    val getRecommendedCourses: GetRecommendedCourses,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
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
            getRecommendedCoursesCase()
        }
        viewModelScope.launch {
            secureTokenStore.saveLoginState(true)
        }
    }

    suspend fun getRecommendedCoursesCase() {
        getRecommendedCourses().collect { courses ->
            _uiState.update {
                it.copy(
                    recommendedCourses = courses
                )
            }
        }

    }


}