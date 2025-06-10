package org.mahd_e_learning_platform.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import org.mahd_e_learning_platform.presentation.screens.login.LoginScreen
import org.mahd_e_learning_platform.presentation.screens.onboarding.OnBoarding
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MahdELearningPlatformTheme {
                LoginScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}