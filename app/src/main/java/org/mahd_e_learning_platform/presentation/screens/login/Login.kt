package org.mahd_e_learning_platform.presentation.screens.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState = loginViewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.background(
            brush = Brush.horizontalGradient(
                listOf(
                    MahdELearningPlatformTheme.colors.secondary,
                    MahdELearningPlatformTheme.colors.white
                )
            )
        ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        stickyHeader {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(
                        60.dp,
                        65.dp
                    )
                )
                Text(
                    text = stringResource(R.string.welcome),
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.black
                )
                Text(
                    text = stringResource(R.string.sign_in_to_continue_or_create_a_new_account),
                    style = MahdELearningPlatformTheme.typography.bodyLarge,
                    color = MahdELearningPlatformTheme.colors.subText
                )
            }
        }
        item {
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
        }
        item {
            SignInCard(
                modifier = Modifier.padding(horizontal = MahdELearningPlatformTheme.dimin.mediumPadding),
                onEmailTextChange = { loginViewModel.onEmailTextChanged(it) },
                onPasswordTextChange = { loginViewModel.onPasswordTextChanged(it) },
                onChecked = { loginViewModel.isChecked(it) },
                email = uiState.value.email,
                password = uiState.value.password,
                isChecked = uiState.value.isRemembered,
            )
        }

    }


}


@Composable
fun SignInCard(
    modifier: Modifier = Modifier,
    password: String = "",
    email: String = "",
    isChecked: Boolean = false,
    onEmailTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onChecked: (Boolean) -> Unit,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MahdELearningPlatformTheme.colors.background
        ),
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MahdELearningPlatformTheme.dimin.mediumPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()

                ) {
                Text(
                    stringResource(R.string.signin),
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.black
                )
                Text(
                    stringResource(R.string.login_title),
                    style = MahdELearningPlatformTheme.typography.bodyMedium,
                    color = MahdELearningPlatformTheme.colors.subText,
                )

            }
            Column {
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailTextChange,
                    placeholder = { Text(stringResource(id = R.string.enter_your_email)) },
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
            }
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))
            OutlinedTextField(
                value = password,
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

        }
    }
}

@Preview
@Composable
private fun SignInCardPreview() {
    LoginScreen(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}