package org.mahd_e_learning_platform.presentation.screens.onboarding.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme


@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit,
) {

    AnimatedVisibility(visible = pagerState.currentPage == 1) {

        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth(fraction = MahdELearningPlatformTheme.dimin.largeFraction)
                .statusBarsPadding(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MahdELearningPlatformTheme.colors.text,
                contentColor = MahdELearningPlatformTheme.colors.background,
            ),
            ) {
            Text(text = stringResource(R.string.finish))
        }
    }


}