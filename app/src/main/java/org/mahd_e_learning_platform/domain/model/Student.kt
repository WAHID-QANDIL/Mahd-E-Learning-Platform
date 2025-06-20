package org.mahd_e_learning_platform.domain.model

data class Student(
    val firstName: String,
    val lastname: String,
    val email: String,
    val role: String = "student",
)