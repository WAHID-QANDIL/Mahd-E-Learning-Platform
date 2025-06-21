package org.mahd_e_learning_platform.presentation.screens.exams.my_exams

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyExamsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyExamsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadExams()
    }

    private fun loadExams() {
        // In a real app, this data would come from a repository
        val dummyExams = listOf(
            ExamItem(1, "Mathematics Quiz", "Algebra & Geometry", 45, 30, ExamStatus.LIVE, "Due: Today 6:00 PM"),
            ExamItem(2, "Physics Test", "Mechanics & Waves", 60, 25, ExamStatus.UPCOMING, "Starts: Tomorrow 2:00 PM"),
            ExamItem(3, "Chemistry Quiz", "Organic Chemistry", 30, 20, ExamStatus.COMPLETED, "Completed on: 19 Jun 2024", 85)
        )
        _uiState.update {
            it.copy(
                allExams = dummyExams,
                filteredExams = dummyExams // Initially, show all
            )
        }
    }

    fun onFilterChange(filter: ExamFilter) {
        _uiState.update { currentState ->
            val filteredList = when (filter) {
                ExamFilter.ALL -> currentState.allExams
                ExamFilter.UPCOMING -> currentState.allExams.filter { it.status == ExamStatus.UPCOMING }
                ExamFilter.COMPLETED -> currentState.allExams.filter { it.status == ExamStatus.COMPLETED }
            }
            currentState.copy(
                selectedFilter = filter,
                filteredExams = filteredList
            )
        }
    }

    fun onStartExam(examId: Int) {
        // TODO: Handle navigation to start the exam
    }

    fun onViewResults(examId: Int) {
        // TODO: Handle navigation to view exam results
    }

    fun onSearchClicked() {
        // TODO: Handle search action
    }

    fun onNavigateBack() {
        // TODO: Handle back navigation
    }
}