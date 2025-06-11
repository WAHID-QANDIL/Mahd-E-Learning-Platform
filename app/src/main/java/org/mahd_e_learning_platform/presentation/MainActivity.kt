package org.mahd_e_learning_platform.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.mahd_e_learning_platform.presentation.screens.welcome.WelcomeScreen
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
//        splashScreen.setKeepOnScreenCondition {
//            false
//        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MahdELearningPlatformTheme {
                WelcomeScreen(modifier = Modifier.fillMaxSize().systemBarsPadding())
            }
        }
    }
}