package org.mahd_e_learning_platform.presentation.screens.profile.profile_menu

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import org.mahd_e_learning_platform.R

// Represents a single clickable item in the profile list
data class ProfileMenuItem(
    val title: String,
    val icon: ImageVector,
    // Optional subtitle for items like "Language" or "My Saved Courses"
    val subtitle: String? = null,
    // To distinguish between a navigation arrow and a switch
    val hasSwitch: Boolean = false
)

// Represents the user's information
data class UserInfo(
    val name: String = "Sarah Johnson",
    val email: String = "sarah.johnson@example.com",
    val profileImage: Int = R.drawable.instructor_sarah
)

// The main state holder for the entire Profile screen
data class ProfileUiState(
    val userInfo: UserInfo = UserInfo(),
    val isDarkMode: Boolean = false,
    val menuItems: Map<String, List<ProfileMenuItem>> = mapOf(
        "Personal Information" to listOf(
            ProfileMenuItem("Account Details", Icons.Default.AccountCircle),
            ProfileMenuItem("Security", Icons.Default.Security)
        ),
        "Settings" to listOf(
            ProfileMenuItem("Language", Icons.Default.Language, subtitle = "English"),
            ProfileMenuItem("Dark Mode", Icons.Default.DarkMode, hasSwitch = true)
        ),
        "Payment History" to listOf(
            ProfileMenuItem("Payment Methods", Icons.Default.Payment, subtitle = "Visa ending in 4242"),
            ProfileMenuItem("Transaction History", Icons.Default.History)
        ),
        "Saved Courses" to listOf(
            ProfileMenuItem("My Saved Courses", Icons.Default.Bookmark, subtitle = "12 courses saved")
        )
    )
)