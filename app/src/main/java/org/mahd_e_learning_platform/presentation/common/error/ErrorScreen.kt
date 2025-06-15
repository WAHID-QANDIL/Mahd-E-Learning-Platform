package org.mahd_e_learning_platform.presentation.common.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    viewModel: ErrorViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    // The content will only be displayed when the uiState is not null
    uiState?.let {
        ErrorContent(
            modifier = modifier,
            uiState = it,
            onAction = { when (it) {
                ActionKey.TRY_AGAIN, ActionKey.RETRY_CONNECTION -> {

                }
                ActionKey.GO_BACK  -> {

                }
                ActionKey.GO_HOME ->  {

                }
                ActionKey.CONTACT_SUPPORT -> {

                }
            } }
        )
    }
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    uiState: ErrorUiState,
    onAction: (ActionKey) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MahdELearningPlatformTheme.colors.background)
            .padding(MahdELearningPlatformTheme.dimin.largePadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main error card
        Card(
            shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding)),
            colors = CardDefaults.cardColors(containerColor = MahdELearningPlatformTheme.colors.white),
            elevation = CardDefaults.cardElevation(defaultElevation = MahdELearningPlatformTheme.dimin.smallPadding)
        ) {
            Column(
                modifier = Modifier.padding(MahdELearningPlatformTheme.dimin.largePadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = uiState.errorIcon),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = MahdELearningPlatformTheme.colors.error
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
                Text(
                    text = uiState.errorTitle,
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.black
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))
                Text(
                    text = uiState.errorMessage,
                    style = MahdELearningPlatformTheme.typography.bodyLarge,
                    color = MahdELearningPlatformTheme.colors.subText,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

                // Error details section (only shown if code and status exist)
                if (uiState.errorCode != null && uiState.errorStatus != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MahdELearningPlatformTheme.colors.background,
                                shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding))
                            )
                            .padding(MahdELearningPlatformTheme.dimin.mediumPadding)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Error Code:", style = MahdELearningPlatformTheme.typography.bodyMedium)
                            Text(uiState.errorCode.toString(), style = MahdELearningPlatformTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Status:", style = MahdELearningPlatformTheme.typography.bodyMedium)
                            Text(
                                uiState.errorStatus,
                                style = MahdELearningPlatformTheme.typography.bodyMedium,
                                color = MahdELearningPlatformTheme.colors.error,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

                // DYNAMIC ACTION BUTTONS
                uiState.actions.forEach { action ->
                    when (action.type) {
                        ErrorActionType.PRIMARY -> {
                            Button(
                                onClick = { onAction(action.key) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(action.text)
                            }
                        }
                        ErrorActionType.SECONDARY -> {
                            OutlinedButton(
                                onClick = { onAction(action.key) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(action.text)
                            }
                        }
                    }
                    Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))
                }
            }
        }

        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

        // "Need Help?" card
        Card(
            shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding)),
            colors = CardDefaults.cardColors(containerColor = MahdELearningPlatformTheme.colors.background),
        ) {
            Column(
                modifier = Modifier.padding(MahdELearningPlatformTheme.dimin.largePadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.need_help),
                    style = MahdELearningPlatformTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))
                Text(
                    text = stringResource(R.string.if_this_problem_persists),
                    style = MahdELearningPlatformTheme.typography.bodyMedium,
                    color = MahdELearningPlatformTheme.colors.subText,
                    textAlign = TextAlign.Center
                )
                TextButton(onClick = { onAction(ActionKey.CONTACT_SUPPORT) }) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
                    Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                    Text(stringResource(R.string.contact_support))
                }
            }
        }
    }
}