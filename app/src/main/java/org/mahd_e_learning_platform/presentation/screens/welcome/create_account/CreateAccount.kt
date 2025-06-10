package org.mahd_e_learning_platform.presentation.screens.welcome.create_account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun CreateAccountCard(
    modifier: Modifier = Modifier,
    uiState: CreateAccountUiState,
    onFirstNameTextChange: (String) -> Unit,
    onLastNameTextChange: (String) -> Unit,
    onEmailTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onChecked: (Boolean) -> Unit,
    onCreateAccount: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MahdELearningPlatformTheme.colors.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MahdELearningPlatformTheme.dimin.largePadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(R.string.create_account),
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.black
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
                Text(
                    text = stringResource(R.string.join_us_today_and_get_started),
                    style = MahdELearningPlatformTheme.typography.bodyLarge,
                    color = MahdELearningPlatformTheme.colors.subText,
                    maxLines = 2,
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.firstName,
                        onValueChange = onFirstNameTextChange,
                        placeholder = { Text(stringResource(R.string.john)) },
                        label = {
                            Text(
                                text = stringResource(R.string.first_name),
                                style = MahdELearningPlatformTheme.typography.bodyLarge
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = MahdELearningPlatformTheme.colors.textFieldIndicatorColor
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.lastName,
                        onValueChange = onLastNameTextChange,
                        placeholder = { Text(stringResource(R.string.doe)) },
                        label = {
                            Text(
                                text = stringResource(R.string.last_name),
                                style = MahdELearningPlatformTheme.typography.bodyLarge
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor = MahdELearningPlatformTheme.colors.textFieldIndicatorColor
                        )
                    )
                }
            }
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
            OutlinedTextField(
                value = uiState.email,
                onValueChange = onEmailTextChange,
                placeholder = { Text(stringResource(R.string.enter_your_email)) },
                label = {
                    Text(
                        text = stringResource(R.string.email_address),
                        style = MahdELearningPlatformTheme.typography.bodyLarge
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = stringResource(R.string.email_address)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = MahdELearningPlatformTheme.colors.textFieldIndicatorColor
                )
            )
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordTextChange,
                placeholder = { Text(text = stringResource(R.string.enter_your_password)) },
                label = {
                    Text(
                        text = stringResource(R.string.password),
                        style = MahdELearningPlatformTheme.typography.bodyLarge
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = stringResource(R.string.password)
                    )
                },
                shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.smallPadding))
            )
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = uiState.isRemembered,
                    onCheckedChange = onChecked
                )
                Text(
                    text = stringResource(R.string.i_agree_to_the_terms_of_service_and_privacy_policy),
                    style = MahdELearningPlatformTheme.typography.bodyLarge,
                    color = MahdELearningPlatformTheme.colors.subText,
                )
            }
            Button(
                onClick = onCreateAccount,
                shape = RoundedCornerShape(corner = CornerSize(MahdELearningPlatformTheme.dimin.smallPadding)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MahdELearningPlatformTheme.colors.primary,
                ),
                contentPadding = PaddingValues(
                    horizontal = MahdELearningPlatformTheme.dimin.largePadding,
                    vertical = MahdELearningPlatformTheme.dimin.mediumPadding
                ),
                modifier = Modifier.fillMaxWidth(fraction = MahdELearningPlatformTheme.dimin.largeFraction)
            ) {
                Text(
                    text = stringResource(R.string.create_account),
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.white
                )
            }
        }
    }
    Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))
}