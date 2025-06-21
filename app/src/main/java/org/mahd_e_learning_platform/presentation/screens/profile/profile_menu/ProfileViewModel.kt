package org.mahd_e_learning_platform.presentation.screens.profile.profile_menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun onDarkModeToggled(isDarkMode: Boolean) {
        _uiState.update { it.copy(isDarkMode = isDarkMode) }
    }

    fun onEditProfileClicked() {
        // TODO: Handle navigation to edit profile screen
    }

    fun onMenuItemClicked(item: ProfileMenuItem) {
        // TODO: Handle navigation for each menu item based on its title or a key
    }

    fun onLogoutClicked() {
        // TODO: Handle user logout
    }

    fun onMoreOptionsClicked() {
        // TODO: Handle "more" menu click
    }

    fun onNavigateBack() {
        // TODO: Handle back navigation
    }
}