package org.mahd_e_learning_platform.presentation.screens.course_details

import org.mahd_e_learning_platform.R

// Represents the complete state for the Course Details screen
data class CourseDetailsUiState(
    val courseTitle: String = "Complete UI/UX Design Masterclass",
    val courseDescription: String = "Learn modern design principles, prototyping, and user experience fundamentals from industry experts.",
    val courseImage: Int = R.drawable.course_details_image, // Placeholder
    val category: String = "Design",
    val rating: Float = 4.8f,
    val reviewCount: String = "2.4k",
    val hours: Int = 12,
    val lessons: Int = 24,
    val hasCertificate: Boolean = true,
    val isFavorited: Boolean = false,
    val instructor: InstructorInfo = InstructorInfo(),
    val pricing: PricingInfo = PricingInfo()
)

// Represents the instructor information section
data class InstructorInfo(
    val name: String = "Sarah Johnson",
    val title: String = "Senior UX Designer at Google",
    val imageUrl: Int = R.drawable.instructor_sarah, // Placeholder
    val isFollowed: Boolean = false
)

// Represents the pricing and enrollment section
data class PricingInfo(
    val currentPrice: Int = 89,
    val originalPrice: Int = 149,
    val discountPercent: Int = 40
)