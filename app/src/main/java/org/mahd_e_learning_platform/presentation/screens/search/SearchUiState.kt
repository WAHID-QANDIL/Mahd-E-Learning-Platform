package org.mahd_e_learning_platform.presentation.screens.search

import org.mahd_e_learning_platform.domain.model.Course

data class SearchUiState(
    val searchQuery: String = "",
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)