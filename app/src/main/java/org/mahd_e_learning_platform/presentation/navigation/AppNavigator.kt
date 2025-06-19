package org.mahd_e_learning_platform.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.mahd_e_learning_platform.presentation.common.error.ErrorScreen
import org.mahd_e_learning_platform.presentation.screens.auth.WelcomeScreen
import org.mahd_e_learning_platform.presentation.screens.auth.forgot_password.ForgotPasswordScreen
import org.mahd_e_learning_platform.presentation.screens.home.HomeScreen
import org.mahd_e_learning_platform.presentation.screens.onboarding.OnBoarding


@Composable
fun AppNavigator(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
    ) {


    NavHost(
        navController = navHostController,
        startDestination = Screen.Welcome.destination.rout
    ){

        composable(route = Screen.Welcome.destination.rout) {
            WelcomeScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }

        composable(route = Screen.Home.destination.rout) {
            HomeScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }

        composable(route = Screen.ForgetPassword.destination.rout) {
            ForgotPasswordScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }
        composable(route = Screen.Error.destination.rout) {
            ErrorScreen(
                navHostController = navHostController
            )
        }
        composable(route = Screen.OnBoarding.destination.rout) {
            OnBoarding(
                navHostController = navHostController
            )
        }


    }

}