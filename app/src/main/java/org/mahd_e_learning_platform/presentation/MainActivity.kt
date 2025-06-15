package org.mahd_e_learning_platform.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.presentation.common.error.ErrorScreen
import org.mahd_e_learning_platform.presentation.common.error.ErrorType
import org.mahd_e_learning_platform.presentation.common.error.ErrorViewModel
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
//        splashScreen.setKeepOnScreenCondition {
//            false
//        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                getColor(R.color.primary)
            ),
            navigationBarStyle = SystemBarStyle.dark(
                getColor(R.color.primary)
            ),
        )
        setContent {
            MahdELearningPlatformTheme {
                val viewModel: ErrorViewModel = hiltViewModel()



                // ErrorType.SERVER_ERROR
                // ErrorType.PAGE_NOT_FOUND
                // ErrorType.NO_INTERNET
                // ErrorType.REQUEST_TIMEOUT

                LaunchedEffect(key1 = Unit) {
                    viewModel.showError(ErrorType.SERVER_ERROR)
                }

                ErrorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    viewModel = viewModel
                )

            }
        }
    }
}