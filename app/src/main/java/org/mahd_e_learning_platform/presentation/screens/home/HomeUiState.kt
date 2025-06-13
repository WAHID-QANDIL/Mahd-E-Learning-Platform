package org.mahd_e_learning_platform.presentation.screens.home

import org.mahd_e_learning_platform.domain.model.Course
import org.mahd_e_learning_platform.domain.model.Student

data class HomeUiState(
    val student: Student? = null,
    val course: Course? = null,
    val categoriesUiState: CategoriesUiState? = null,
    val recommendedCourses: List<Course>? = null
)