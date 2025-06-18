package org.mahd_e_learning_platform.presentation.screens.course_details

import org.mahd_e_learning_platform.R

// Represents the complete state for the Course Details screen
data class CourseDetailsUiState(
    val courseTitle: String = "",
    val courseDescription: String = "",
    val courseImage: Int = R.drawable.course_details_image, // Placeholder
    val category: String = "",
    val rating: Float = 4.8f,
    val reviewCount: String = "2.4k",
    val hours: Int = 12,
    val lessons: Int = 24,
    val hasCertificate: Boolean = true,
    val isFavorited: Boolean = false,
    val instructor: InstructorInfo = InstructorInfo(),
    val pricing: PricingInfo = PricingInfo(
        currentPrice = 0,
        originalPrice = 0,
        discountPercent = 0
    )
)

// Represents the instructor information section
data class InstructorInfo(
    val name: String = "",
    val title: String = "",
    val imageUrl: Int = R.drawable.instructor_sarah, // Placeholder
    val isFollowed: Boolean = false
)

// Represents the pricing and enrollment section
data class PricingInfo(
    val currentPrice: Int,
    val originalPrice: Int,
    val discountPercent: Int
)