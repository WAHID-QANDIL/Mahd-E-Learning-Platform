package org.mahd_e_learning_platform.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.mahd_e_learning_platform.presentation.screens.welcome.forgot_password.ForgotPasswordScreen
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
              
                ForgotPasswordScreen(
                    modifier = Modifier.fillMaxSize().systemBarsPadding(),
                    onBackToLogin = { /* Navigation logic will go here */ },
                    onBackClicked = { /* Navigation logic will go here */ }
                )
            }
        }
    }
}