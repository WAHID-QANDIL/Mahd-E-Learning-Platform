package org.mahd_e_learning_platform.utils

import androidx.annotation.DrawableRes
import org.mahd_e_learning_platform.R

sealed class OnBoarding(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {

    data object First:OnBoarding(
        image = R.drawable.onboarding1,
        title = "learn anything anywhere",
        description = "discover thousands of courses and expand your knowledge with our interactive learning platform"
    )
    data object Second:OnBoarding(
        image = R.drawable.onboarding2,
        title = "Welcome to Mahd",
        description = "Discover thousands of courses and unlock your potential with personalized learning experiences."
    )

}