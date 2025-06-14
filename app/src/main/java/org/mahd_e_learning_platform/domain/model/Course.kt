package org.mahd_e_learning_platform.domain.model

data class Course(
    val courseId: String,
    val courseImageUrl: String,
    val courseTitle: String,
    val courseDescription: String,
    val enrollments: Int,
    val rate: Float,
    val cost: Int,
    val progress: Float = 0f,
    val educator: String,
    val level: String,
    val sections: List<Any>,
    val category: List<String>,


    )