package org.mahd_e_learning_platform.presentation.screens.auth.login

import androidx.compose.foundation.clickable
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

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun LoginScreen(
//    modifier: Modifier = Modifier,
//    loginViewModel: LoginViewModel = hiltViewModel(),
//) {
//    val uiState = loginViewModel.uiState.collectAsStateWithLifecycle()
//
//    LazyColumn(
//        modifier = modifier.background(
//            brush = Brush.horizontalGradient(
//                listOf(
//                    MahdELearningPlatformTheme.colors.secondary,
//                    MahdELearningPlatformTheme.colors.white
//                )
//            )
//        ),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        ) {
//        stickyHeader {
//            Column(
//                modifier = Modifier.fillMaxWidth(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Image(
//                    painter = painterResource(R.drawable.app_logo),
//                    contentDescription = stringResource(R.string.app_name),
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.size(
//                        60.dp,
//                        65.dp
//                    )
//                )
//                Text(
//                    text = stringResource(R.string.welcome),
//                    style = MahdELearningPlatformTheme.typography.titleLarge,
//                    color = MahdELearningPlatformTheme.colors.black
//                )
//                Text(
//                    text = stringResource(R.string.sign_in_to_continue_or_create_a_new_account),
//                    style = MahdELearningPlatformTheme.typography.bodyLarge,
//                    color = MahdELearningPlatformTheme.colors.subText
//                )
//            }
//        }
//        item {
//            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
//        }
//        item {
//            SignInCard(
//                modifier = Modifier.padding(horizontal = MahdELearningPlatformTheme.dimin.mediumPadding),
//                onEmailTextChange = { loginViewModel.onEmailTextChanged(it) },
//                onPasswordTextChange = { loginViewModel.onPasswordTextChanged(it) },
//                onChecked = { loginViewModel.isChecked(it) },
//                email = uiState.value.email,
//                password = uiState.value.password,
//                isChecked = uiState.value.isRemembered,
//            )
//        }
//
//    }
//
//
//}


@Composable
fun SignInCard(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onChecked: (Boolean) -> Unit,
    onForgetPasswordClicked: () -> Unit,
    onSignIn: () -> Unit,
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
                .padding(MahdELearningPlatformTheme.dimin.largePadding),
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
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
                Text(
                    stringResource(R.string.login_title),
                    style = MahdELearningPlatformTheme.typography.bodyLarge,
                    color = MahdELearningPlatformTheme.colors.subText,
                    maxLines = 2,
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

            }
            Column {
                OutlinedTextField(
                    value = uiState.email,
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Checkbox(
                        checked = uiState.isRemembered,
                        onCheckedChange = onChecked
                    )
                    Text(
                        text = stringResource(R.string.remember_me),
                        style = MahdELearningPlatformTheme.typography.bodyLarge,
                        color = MahdELearningPlatformTheme.colors.subText,
                    )
                }
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MahdELearningPlatformTheme.typography.bodyLarge,
                    color = MahdELearningPlatformTheme.colors.primary,
                    modifier = Modifier.clickable(onClick = onForgetPasswordClicked)
                )

            }
            Button(
                onClick = onSignIn,
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
                    text = stringResource(R.string.sign_in),
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.white
                )
            }

        }
    }
}

//@Preview
//@Composable
//private fun SignInCardPreview() {
//    LoginScreen(
//        modifier = Modifier
//            .fillMaxSize()
//            .statusBarsPadding()
//    )
//}