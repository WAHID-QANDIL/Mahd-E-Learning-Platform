package org.mahd_e_learning_platform.presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.presentation.common.HorizontalPagerIndicator
import org.mahd_e_learning_platform.presentation.navigation.Screen
import org.mahd_e_learning_platform.presentation.screens.onboarding.component.FinishButton
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
import org.mahd_e_learning_platform.utils.OnBoarding
import org.mahd_e_learning_platform.utils.onBoardingImagehight

@Composable
fun OnBoarding(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val pages = listOf(
        OnBoarding.First,
        OnBoarding.Second
    )

    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )
    Box {
        Column(
            modifier = modifier,
        ) {
            HorizontalPager(state = pagerState) { index ->
                OnBoardingScreenContent(modifier = modifier, onBoarding = pages[index])
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(MahdELearningPlatformTheme.dimin.mediumPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                HorizontalPagerIndicator(pagerState = pagerState)
                FinishButton(
                    pagerState = pagerState,
                    modifier = Modifier.fillMaxWidth(fraction = MahdELearningPlatformTheme.dimin.largeFraction)
                ) {
                    navHostController.navigate(Screen.Welcome.destination.rout)
                }
            }

        }
    }


}

@Composable
fun OnBoardingScreenContent(
    modifier: Modifier = Modifier,
    onBoarding: OnBoarding,
) {


    Box(
        modifier = modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MahdELearningPlatformTheme.colors.background,
                    MahdELearningPlatformTheme.colors.primary,
                    MahdELearningPlatformTheme.colors.background,
                ),
                tileMode = TileMode.Clamp,
            )
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(onBoarding.image), contentDescription = stringResource(
                    R.string.onboarding
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = 0.99f }
                    .drawWithContent {
                        val colors = listOf(Color.Black, Color.Transparent)
                        drawContent()
                        drawRect(
                            brush = Brush.linearGradient(
                                colors = colors

                            ),
                        )


                    },

                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .statusBarsPadding(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    onBoarding.title,
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
                Text(
                    onBoarding.description,
                    style = MahdELearningPlatformTheme.typography.bodyMedium,
                    color = MahdELearningPlatformTheme.colors.subText,
                    textAlign = TextAlign.Center
                )


            }


        }


    }
}
