package org.mahd_e_learning_platform.presentation.screens.payment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.mahd_e_learning_platform.presentation.screens.payment.EnrollmentSuccessUiState

class EnrollmentSuccessViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(EnrollmentSuccessUiState())
    val uiState = _uiState

    fun onStartLearningNow() {
        // TODO: Handle navigation to the course player
    }

    fun onViewCourseDetails() {
        // TODO: Handle navigation to the course details screen
    }

    fun onContactSupport() {
        // TODO: Handle navigation or action for contacting support
    }
}