package org.mahd_e_learning_platform.presentation.screens.auth

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.data.exception.ExceptionHandler
import org.mahd_e_learning_platform.presentation.navigation.Screen
import org.mahd_e_learning_platform.presentation.screens.auth.create_account.CreateAccountCard
import org.mahd_e_learning_platform.presentation.screens.auth.create_account.CreateAccountViewModel
import org.mahd_e_learning_platform.presentation.screens.auth.login.LoginViewModel
import org.mahd_e_learning_platform.presentation.screens.auth.login.SignInCard
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    createAccountViewModel: CreateAccountViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {

    val loginUiState = loginViewModel.uiState.collectAsStateWithLifecycle().value
    val createAccountUiState = createAccountViewModel.uiState.collectAsStateWithLifecycle().value

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
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(
                        100.dp,
                        80.dp
                    )
                )

                    Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

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
                uiState = loginUiState,
                onForgetPasswordClicked = {
                    navHostController.navigate(Screen.ForgetPassword.destination.rout) {
                        popUpTo(0)

                    }
                },
                onSignIn = {
                    loginViewModel.onSignIn(
                        email = loginUiState.email,
                        password = loginUiState.password,
                        onSuccess = { navHostController.navigate(Screen.Home.destination.rout) }
                    )
                    Log.d("WelcomeScreen", "WelcomeScreen: ${loginUiState.isSuccess}")
                },
            )
        }
        item {
            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))
        }
        item {
            CreateAccountCard(
                modifier = Modifier.padding(horizontal = MahdELearningPlatformTheme.dimin.mediumPadding),
                uiState = createAccountUiState,
                onEmailTextChange = { createAccountViewModel.onEmailTextChanged(it) },
                onPasswordTextChange = { createAccountViewModel.onPasswordTextChanged(it) },
                onChecked = { createAccountViewModel.isChecked(it) },
                onCreateAccount = {
                    createAccountViewModel.onCreateAccount(
                        firstName = createAccountUiState.firstName,
                        lastName = createAccountUiState.lastName,
                        email = createAccountUiState.email,
                        password = createAccountUiState.password,
                    )
                },
                onFirstNameTextChange = { createAccountViewModel.onFirstNameChange(it) },
                onLastNameTextChange = { createAccountViewModel.onLastNameChange(it) }
            )
        }

    }
    LaunchedEffect(key1 = loginUiState.error) {

        if (!loginUiState.error.isNullOrEmpty()) {
            when (loginUiState.error) {
                ExceptionHandler.UnauthorizedException().message -> {
                    navHostController.navigate(Screen.Error.destination.rout)
                }

                ExceptionHandler.ServerErrorException().message -> {
                    navHostController.navigate(Screen.Error.destination.rout)
                }

                else -> navHostController.navigate(Screen.Error.destination.rout)
            }
        } else {
            if (loginUiState.isSuccess) {
                navHostController.navigate(Screen.Home.destination.rout)
            }
        }


    }

}