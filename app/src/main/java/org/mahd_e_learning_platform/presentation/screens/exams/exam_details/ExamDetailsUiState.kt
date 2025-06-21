package org.mahd_e_learning_platform.presentation.screens.exams.exam_details


import org.mahd_e_learning_platform.R

// Represents the complete state for the Exam Details screen
data class ExamDetailsUiState(
    val quizTitle: String = "Mathematics Quiz",
    val examTopic: String = "Algebra Basics",
    val examDescription: String = "Test your knowledge of algebraic concepts",
    val iconResId: Int = R.drawable.ic_calculator, // Placeholder for the calculator icon
    val questionCount: Int = 15,
    val durationInMinutes: Int = 20,
    val passRatePercent: Int = 75,
    val instructions: List<String> = listOf(
        "Choose the best answer for each question",
        "You can review answers before submitting",
        "Timer will start when you begin"
    )
)