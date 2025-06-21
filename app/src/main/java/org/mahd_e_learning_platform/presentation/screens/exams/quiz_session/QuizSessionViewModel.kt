package org.mahd_e_learning_platform.presentation.screens.exams.quiz_session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizSessionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizSessionUiState())
    val uiState = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.remainingTimeSeconds > 0) {
                delay(1000)
                _uiState.update { it.copy(remainingTimeSeconds = it.remainingTimeSeconds - 1) }
            }
        }
    }

    fun onOptionSelected(optionId: String) {
        _uiState.update { it.copy(selectedOptionId = optionId) }
    }

    fun onSkipQuestion() {
        // TODO: Implement logic to skip to the next question
    }

    fun onNextQuestion() {
        // TODO: Implement logic to submit the answer and go to the next question
    }

    fun onMoreOptionsClicked() {
        // TODO: Handle more options menu click
    }

    fun onNavigateBack() {
        // TODO: Handle back navigation
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel() // Ensure the timer is stopped when the ViewModel is destroyed
    }
}