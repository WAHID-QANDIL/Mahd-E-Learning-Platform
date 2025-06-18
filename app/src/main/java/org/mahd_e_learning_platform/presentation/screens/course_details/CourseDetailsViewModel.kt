package org.mahd_e_learning_platform.presentation.screens.course_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CourseDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CourseDetailsUiState())
    val uiState = _uiState

    fun onToggleFavorite() {
        _uiState.update { currentState ->
            currentState.copy(isFavorited = !currentState.isFavorited)
        }
    }

    fun onToggleFollowInstructor() {
        _uiState.update { currentState ->
            val updatedInstructor = currentState.instructor.copy(
                isFollowed = !currentState.instructor.isFollowed
            )
            currentState.copy(instructor = updatedInstructor)
        }
    }

    fun onEnrollNow() {
        // TODO: Handle enrollment logic
    }

    fun onNavigateBack() {
        // TODO: Handle back navigation
    }
}