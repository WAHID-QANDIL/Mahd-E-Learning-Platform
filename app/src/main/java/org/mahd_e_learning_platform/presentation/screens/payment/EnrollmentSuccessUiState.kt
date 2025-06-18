package org.mahd_e_learning_platform.presentation.screens.payment

import org.mahd_e_learning_platform.R

// Main state holder for the screen
data class EnrollmentSuccessUiState(
    val courseInfo: CourseInfo = CourseInfo()
)

// Represents the data for the course information card
data class CourseInfo(
    val title: String = "Mobile App Development",
    val duration: String = "12 weeks",
    val level: String = "Beginner Level",
     // Placeholder for a drawable resource
     val icon: Int = R.drawable.ic_launcher_foreground
) {

}