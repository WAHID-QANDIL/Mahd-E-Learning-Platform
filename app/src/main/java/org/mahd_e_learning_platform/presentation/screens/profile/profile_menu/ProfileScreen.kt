package org.mahd_e_learning_platform.presentation.screens.profile.profile_menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onNavigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.onMoreOptionsClicked() }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ProfileHeader(
                    userInfo = uiState.userInfo,
                    onEditProfile = { viewModel.onEditProfileClicked() }
                )
            }

            uiState.menuItems.forEach { (groupTitle, items) ->
                item {
                    MenuGroup(
                        groupTitle = groupTitle,
                        items = items,
                        isDarkMode = uiState.isDarkMode,
                        onDarkModeToggled = { viewModel.onDarkModeToggled(it) },
                        onMenuItemClicked = { viewModel.onMenuItemClicked(it) }
                    )
                }
            }

            item {
                LogoutButton(onLogout = { viewModel.onLogoutClicked() })
            }
        }
    }
}

@Composable
private fun ProfileHeader(userInfo: UserInfo, onEditProfile: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 24.dp)
    ) {
        Image(
            painter = painterResource(id = userInfo.profileImage),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.height(16.dp))
        Text(userInfo.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(userInfo.email, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onEditProfile,
            colors = ButtonDefaults.buttonColors(
                containerColor = MahdELearningPlatformTheme.colors.primary, // Sets the background color of the button
                contentColor = Color.White // Sets the color of the text and icon by default
            )
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = null, // Or provide a meaningful description
                modifier = Modifier.size(ButtonDefaults.IconSize),
                // tint = Color.White // Explicitly set tint if needed, defaults to contentColor from Button
            )
            Spacer(Modifier.width(ButtonDefaults.IconSpacing))
            Text("Edit Profile")
        }
    }
}

@Composable
private fun MenuGroup(
    groupTitle: String,
    items: List<ProfileMenuItem>,
    isDarkMode: Boolean,
    onDarkModeToggled: (Boolean) -> Unit,
    onMenuItemClicked: (ProfileMenuItem) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = groupTitle,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        items.forEach { item ->
            MenuItemRow(
                item = item,
                isDarkMode = isDarkMode,
                onDarkModeToggled = onDarkModeToggled,
                onClicked = { onMenuItemClicked(item) }
            )
            HorizontalDivider(modifier = Modifier.padding(start = 56.dp))
        }
    }
}


@Composable
private fun MenuItemRow(
    item: ProfileMenuItem,
    isDarkMode: Boolean,
    onDarkModeToggled: (Boolean) -> Unit,
    onClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClicked)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(item.icon, contentDescription = item.title, tint = MahdELearningPlatformTheme.colors.primary)
        Spacer(Modifier.width(24.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(item.title, style = MaterialTheme.typography.bodyLarge)
        }
        if (item.hasSwitch) {
            val switchColors = if (isDarkMode) {
                SwitchDefaults.colors(
                    checkedThumbColor = MahdELearningPlatformTheme.colors.primary, // Color of the thumb when checked
                    checkedTrackColor = MahdELearningPlatformTheme.colors.white, // Color of the track when checked
                    checkedBorderColor = MahdELearningPlatformTheme.colors.primary, // Color of the border when checked
                )
            } else {
                SwitchDefaults.colors() // Uses default Material theme colors
            }
            Switch(
                checked = isDarkMode,
                onCheckedChange = onDarkModeToggled,
                colors = switchColors
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                item.subtitle?.let {
                    Text(it, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                    Spacer(Modifier.width(8.dp))
                }
                Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun LogoutButton(onLogout: () -> Unit) {
    OutlinedButton(
        onClick = onLogout,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MahdELearningPlatformTheme.colors.error),
        border = BorderStroke(1.dp, MahdELearningPlatformTheme.colors.error.copy(alpha = 0.5f))
    ) {
        Text("Log Out")
    }
}

