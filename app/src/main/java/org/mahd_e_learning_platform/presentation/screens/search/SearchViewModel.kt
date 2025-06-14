package org.mahd_e_learning_platform.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mahd_e_learning_platform.domain.model.Course
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchCourses(query)
    }

    private fun searchCourses(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // TODO: Replace with actual repository call
            val filteredCourses = if (query.isNotEmpty()) {
                getDummyCourses().filter {
                    it.courseTitle.contains(query, ignoreCase = true) ||
                            it.courseDescription.contains(query, ignoreCase = true)
                }
            } else {
                emptyList()
            }

            _uiState.update {
                it.copy(
                    courses = filteredCourses,
                    isLoading = false
                )
            }
        }
    }

    private fun getDummyCourses(): List<Course> {
        // Dummy data for testing
        return listOf(
            Course(
                courseId = "1",
                courseTitle = "Complete React Course",
                courseDescription = "Build modern web applications",
                courseImageUrl = "https://picsum.photos/200",
                enrollments = 2500,
                rate = 4.5f,
                cost = 29,
                progress = 0.0f,
                educator = "John Doe",
                level = "Beginner",
                sections = emptyList(),
                category = "Programming"
            ),
            // Add more dummy courses
        )
    }
}