package org.mahd_e_learning_platform.presentation.screens.auth.forgot_password

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.presentation.navigation.Screen
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    ForgotPasswordCard(
        modifier = modifier,
        uiState = uiState.value,
        onEmailTextChange = { viewModel.onEmailTextChanged(it) },
        onSendResetLink = { viewModel.onSendResetLink() },
        onBackToLogin = {
            navHostController.navigate(Screen.Welcome.destination.rout) {
                popUpTo(0)
            }
        },
        onContactSupport = { viewModel.onContactSupport() }
    )
}

@Composable
fun ForgotPasswordCard(
    modifier: Modifier = Modifier,
    uiState: ForgotPasswordUiState,
    onEmailTextChange: (String) -> Unit,
    onSendResetLink: () -> Unit,
    onBackToLogin: () -> Unit,
    onContactSupport: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
//        shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding)),
//        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MahdELearningPlatformTheme.colors.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MahdELearningPlatformTheme.dimin.largePadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back Arrow
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onBackToLogin) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }

            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

            // Lock Icon
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = stringResource(R.string.forgot_password),
                modifier = Modifier
                    .size(80.dp)
                    .padding(MahdELearningPlatformTheme.dimin.mediumPadding),
                tint = MahdELearningPlatformTheme.colors.primary
            )
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

            // Title and Subtitle
            Text(
                text = stringResource(R.string.forgot_password),
                style = MahdELearningPlatformTheme.typography.titleLarge,
                color = MahdELearningPlatformTheme.colors.black
            )
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))
            Text(
                text = stringResource(R.string.forgot_password_subtitle),
                style = MahdELearningPlatformTheme.typography.bodyLarge,
                color = MahdELearningPlatformTheme.colors.subText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = MahdELearningPlatformTheme.dimin.mediumPadding)
            )
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

            // Email Field
            OutlinedTextField(
                value = uiState.email,
                onValueChange = onEmailTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(id = R.string.enter_your_email)) },
                label = {
                    Text(
                        text = stringResource(R.string.email_address),
                        style = MahdELearningPlatformTheme.typography.bodyLarge
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = stringResource(R.string.email_address)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = MahdELearningPlatformTheme.colors.textFieldIndicatorColor
                ),
                singleLine = true
            )
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

            // Send Reset Link Button
            Button(
                onClick = onSendResetLink,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(MahdELearningPlatformTheme.dimin.smallPadding)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MahdELearningPlatformTheme.colors.primary,
                ),
                contentPadding = PaddingValues(vertical = MahdELearningPlatformTheme.dimin.mediumPadding)
            ) {
                Text(
                    text = stringResource(R.string.send_reset_link),
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.white
                )
            }
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

            // Back to Login Button
            TextButton(onClick = onBackToLogin) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                Text(text = stringResource(R.string.back_to_login))
            }
            Spacer(Modifier.weight(1f)) // Pushes content below to the bottom

            // Footer
            Text(
                text = stringResource(R.string.contact_support_prompt),
                style = MahdELearningPlatformTheme.typography.bodyMedium,
                color = MahdELearningPlatformTheme.colors.subText,
            )
            Text(
                text = stringResource(R.string.contact_support),
                modifier = Modifier.clickable(onClick = onContactSupport),
                style = MahdELearningPlatformTheme.typography.bodyMedium,
                color = MahdELearningPlatformTheme.colors.primary
            )
        }
    }
}
