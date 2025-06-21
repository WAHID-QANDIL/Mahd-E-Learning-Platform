package org.mahd_e_learning_platform.presentation.screens.profile.security_c_password

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityScreen(
    modifier: Modifier = Modifier,
    viewModel: SecurityViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Security") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onNavigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                ChangePasswordForm(
                    uiState = uiState,
                    onViewModelEvent = { event ->
                        when(event) {
                            is SecurityEvent.OnCurrentPasswordChanged -> viewModel.onCurrentPasswordChanged(event.value)
                            is SecurityEvent.OnNewPasswordChanged -> viewModel.onNewPasswordChanged(event.value)
                            is SecurityEvent.OnConfirmPasswordChanged -> viewModel.onConfirmNewPasswordChanged(event.value)
                            is SecurityEvent.OnToggleVisibility -> viewModel.toggleVisibility(event.field)
                        }
                    }
                )

                Spacer(Modifier.height(24.dp))

                PasswordRequirements(requirements = uiState.passwordRequirements)

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.onUpdatePasswordClicked() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = uiState.passwordRequirements.allMet && uiState.passwordsMatch
                ) {
                    Text("Update Password", fontSize = 18.sp)
                }
            }
        }
    }
}

// Sealed class for events to make the ViewModel interaction cleaner
sealed class SecurityEvent {
    data class OnCurrentPasswordChanged(val value: String) : SecurityEvent()
    data class OnNewPasswordChanged(val value: String) : SecurityEvent()
    data class OnConfirmPasswordChanged(val value: String) : SecurityEvent()
    data class OnToggleVisibility(val field: String) : SecurityEvent()
}

@Composable
private fun ChangePasswordForm(
    uiState: SecurityUiState,
    onViewModelEvent: (SecurityEvent) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Change Password", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))

            PasswordTextField(
                label = "Current Password",
                value = uiState.currentPassword,
                isVisible = uiState.isCurrentPasswordVisible,
                onValueChange = { onViewModelEvent(SecurityEvent.OnCurrentPasswordChanged(it)) },
                onToggleVisibility = { onViewModelEvent(SecurityEvent.OnToggleVisibility("current")) }
            )
            Spacer(Modifier.height(16.dp))

            PasswordTextField(
                label = "New Password",
                value = uiState.newPassword,
                isVisible = uiState.isNewPasswordVisible,
                onValueChange = { onViewModelEvent(SecurityEvent.OnNewPasswordChanged(it)) },
                onToggleVisibility = { onViewModelEvent(SecurityEvent.OnToggleVisibility("new")) }
            )
            Spacer(Modifier.height(16.dp))

            PasswordTextField(
                label = "Confirm New Password",
                value = uiState.confirmNewPassword,
                isVisible = uiState.isConfirmPasswordVisible,
                onValueChange = { onViewModelEvent(SecurityEvent.OnConfirmPasswordChanged(it)) },
                onToggleVisibility = { onViewModelEvent(SecurityEvent.OnToggleVisibility("confirm")) }
            )
        }
    }
}

@Composable
private fun PasswordTextField(
    label: String,
    value: String,
    isVisible: Boolean,
    onValueChange: (String) -> Unit,
    onToggleVisibility: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onToggleVisibility) {
                Icon(
                    if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Toggle password visibility"
                )
            }
        },
        singleLine = true
    )
}

@Composable
private fun PasswordRequirements(requirements: PasswordRequirements) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MahdELearningPlatformTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Password Requirements:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            RequirementRow("At least 8 characters", requirements.hasMinLength)
            RequirementRow("At least one uppercase letter", requirements.hasUppercase)
            RequirementRow("At least one number", requirements.hasNumber)
            RequirementRow("At least one special character", requirements.hasSpecialChar)
        }
    }
}

@Composable
private fun RequirementRow(text: String, isMet: Boolean) {
    val color = if (isMet) MahdELearningPlatformTheme.colors.primary else Color.Gray
    val icon = if (isMet) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, color = color, style = MaterialTheme.typography.bodyMedium)
    }
}
