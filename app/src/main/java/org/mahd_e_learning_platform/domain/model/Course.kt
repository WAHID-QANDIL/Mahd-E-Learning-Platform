package org.mahd_e_learning_platform.domain.model

data class Course(
    val courseImageUrl: String,
    val courseTitle: String,
    val courseDescription: String,
    val enrollments: Int,
    val rate: Float,
    val cost: Int,
)