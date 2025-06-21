package org.mahd_e_learning_platform.presentation.screens.exams.quiz_session

// Represents a single answer choice for a question
data class AnswerOption(
    val id: String, // e.g., "A", "B"
    val text: String
)

// Represents the current question being displayed
data class Question(
    val questionText: String,
    val questionType: String,
    val options: List<AnswerOption>
)

// The main state holder for the entire Quiz Session screen
data class QuizSessionUiState(
    val quizTitle: String = "Physics Quiz",
    val currentQuestionNumber: Int = 5,
    val totalQuestions: Int = 15,
    val remainingTimeSeconds: Int = 165, // 2 minutes 45 seconds
    val currentQuestion: Question = Question(
        questionText = "What is the acceleration due to gravity on Earth?",
        questionType = "Multiple Choice",
        options = listOf(
            AnswerOption("A", "9.8 m/s²"),
            AnswerOption("B", "10.8 m/s²"),
            AnswerOption("C", "8.8 m/s²"),
            AnswerOption("D", "11.8 m/s²")
        )
    ),
    val selectedOptionId: String? = "B" // The ID of the currently selected answer
) {
    // Calculated property for the progress bar
    val questionProgress: Float
        get() = if (totalQuestions > 0) currentQuestionNumber.toFloat() / totalQuestions.toFloat() else 0f
}