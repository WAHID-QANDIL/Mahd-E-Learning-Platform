package org.mahd_e_learning_platform.presentation.screens.course_player

// Enum to represent the status of a lesson in the content list
enum class LessonStatus {
    LOCKED,
    UNLOCKED,
    WATCHING
}

// Represents a single lesson item in the "Course Content" list
data class LessonItem(
    val id: Int,
    val title: String,
    val durationInMin: Int,
    val status: LessonStatus
)

// Represents the detailed information for the currently playing lesson
data class LessonDetails(
    val title: String = "Introduction to React Components",
    val durationInMin: Int = 15,
    val viewCount: String = "1,234",
    val description: String = "Learn the fundamentals of React components and how to create reusable UI elements that form the building blocks of modern web applications.",
    val isBookmarked: Boolean = false
)

// Represents the user's progress in the course
data class CourseProgress(
    val completedLessons: Int = 4,
    val totalLessons: Int = 12
) {
    val progress: Float
        get() = if (totalLessons > 0) completedLessons.toFloat() / totalLessons.toFloat() else 0f
}

// The main state holder for the entire Course Player screen
data class CoursePlayerUiState(
    val courseTitle: String = "React Fundamentals",
    val currentLesson: LessonDetails = LessonDetails(),
    val courseProgress: CourseProgress = CourseProgress(),
    val lessons: List<LessonItem> = listOf(
        LessonItem(1, "Introduction to React Components", 15, LessonStatus.WATCHING),
        LessonItem(2, "JSX Syntax and Elements", 12, LessonStatus.LOCKED),
        LessonItem(3, "Props and State Management", 18, LessonStatus.LOCKED),
        LessonItem(4, "Event Handling in React", 14, LessonStatus.LOCKED),
        LessonItem(5, "Conditional Rendering", 10, LessonStatus.LOCKED),
        LessonItem(6, "Lists and Keys", 16, LessonStatus.LOCKED),
    )
)