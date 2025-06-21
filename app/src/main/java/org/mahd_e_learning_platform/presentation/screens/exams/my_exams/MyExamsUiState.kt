package org.mahd_e_learning_platform.presentation.screens.exams.my_exams

// Enum to represent the status of an exam, controlling its appearance and actions
enum class ExamStatus {
    LIVE,
    UPCOMING,
    COMPLETED
}

// Enum to represent the selected filter chip
enum class ExamFilter {
    ALL,
    UPCOMING,
    COMPLETED
}

// Data class to hold all information for a single exam card
data class ExamItem(
    val id: Int,
    val title: String,
    val subject: String,
    val durationInMin: Int,
    val questionCount: Int,
    val status: ExamStatus,
    val scheduleInfo: String, // e.g., "Due: Today 6:00 PM" or "Starts: Tomorrow 2:00 PM"
    val score: Int? = null // Only for completed exams
)

// The main state holder for the entire My Exams screen
data class MyExamsUiState(
    val selectedFilter: ExamFilter = ExamFilter.ALL,
    val allExams: List<ExamItem> = emptyList(),
    val filteredExams: List<ExamItem> = emptyList()
)