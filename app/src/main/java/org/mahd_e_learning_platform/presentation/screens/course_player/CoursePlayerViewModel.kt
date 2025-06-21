package org.mahd_e_learning_platform.presentation.screens.course_player

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CoursePlayerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CoursePlayerUiState())
    val uiState = _uiState

    fun onToggleBookmark() {
        _uiState.update { currentState ->
            val updatedLesson = currentState.currentLesson.copy(
                isBookmarked = !currentState.currentLesson.isBookmarked
            )
            currentState.copy(currentLesson = updatedLesson)
        }
    }

    fun onPlayPause() {
        // TODO: Implement video play/pause logic
    }

    fun onNextLesson() {
        // TODO: Implement logic to navigate to the next lesson
    }

    fun onPreviousLesson() {
        // TODO: Implement logic to navigate to the previous lesson
    }

    fun onMoreOptionsSelected() {
        // TODO: Handle "more" menu click
    }

    fun onNavigateBack() {
        // TODO: Handle back navigation
    }
}