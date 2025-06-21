package org.mahd_e_learning_platform.presentation.screens.exams.exam_details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExamDetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ExamDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun onStartExam() {
        // TODO: Implement logic to start the exam
    }

    fun onNavigateBack() {
        // TODO: Implement back navigation
    }
}