package org.mahd_e_learning_platform.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.data.source.remote.auth.SecureTokenStore
import org.mahd_e_learning_platform.presentation.navigation.AppNavigator
import org.mahd_e_learning_platform.presentation.navigation.Screen
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var secureTokenStore: SecureTokenStore
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

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
                val navHostController = rememberNavController()
                var isLoading by remember { mutableStateOf(true) }
                var isLoggedIn by remember { mutableStateOf(false) }
                splashScreen.setKeepOnScreenCondition { isLoading }


                LaunchedEffect(Unit) {
                    isLoggedIn = secureTokenStore.accessLoginState.first().toBoolean()
                    Log.d("isLoggedIn", "onCreate: $isLoggedIn")
                    if (isLoggedIn) {
                        navHostController.navigate(Screen.Home.destination.rout) {
                            popUpTo(0)
                        }
                    } else {
                        navHostController.navigate(Screen.Welcome.destination.rout) {
                            popUpTo(0)
                        }
                    }
                    isLoading = false
                }

                AppNavigator(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(), navHostController =
                        navHostController
                )
            }
        }
    }
}