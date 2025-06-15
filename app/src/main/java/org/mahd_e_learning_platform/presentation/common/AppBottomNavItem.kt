package org.mahd_e_learning_platform.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppBottomNavItem(
    val title: String,
    val icon: ImageVector,
) {
    Home(title = "Home", Icons.Default.Home),
    Search(title = "Search", Icons.Default.Search),
    Courses(title = "My Courses", Icons.Default.Star),
    Profile(title = "Profile", Icons.Default.Person)
}
