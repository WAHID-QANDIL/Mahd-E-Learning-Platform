package org.mahd_e_learning_platform.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.data.source.local.datastore.SecureTokenStore
import org.mahd_e_learning_platform.presentation.common.AppBottomHomeNavBar
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
                val navHostController = rememberNavController()
                var isLoading by remember { mutableStateOf(true) }
                var isLoggedIn by remember { mutableStateOf(false) }
                var isFirstLaunch by remember { mutableStateOf(true) }
                splashScreen.setKeepOnScreenCondition { isLoading }
                val currentRoute = navHostController.currentDestination?.route
                val showBottomBar = remember(currentRoute) {
                    currentRoute == Screen.Home.destination.rout ||
                            currentRoute == Screen.Search.destination.rout ||
                            currentRoute == Screen.MyCourses.destination.rout ||
                            currentRoute == Screen.Profile.destination.rout
                }


                LaunchedEffect(Unit) {
                    isLoggedIn = secureTokenStore.accessLoginState.first()
                    isFirstLaunch = secureTokenStore.accessFirstLaunchState.first()
                    Log.d("isLoggedIn", "onCreate: $isLoggedIn")
                    Log.d("isLoggedIn", "onCreate: $isFirstLaunch")
                    if (isFirstLaunch) {
                        navHostController.navigate(Screen.OnBoarding.destination.rout) {
                            popUpTo(0)
                        }
                    } else if (isLoggedIn) {
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AppNavigator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(9f)
                            .systemBarsPadding(), navHostController =
                            navHostController
                    )
                    if (showBottomBar) {
                        AppBottomHomeNavBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            navHostController = navHostController
                        )
                    }


                }


            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            secureTokenStore.saveFirstLaunch(false)
        }

    }
}