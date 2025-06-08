package org.mahd_e_learning_platform.presentation.common

import android.R.id.primary
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme


@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    )
    {
        repeat(pagerState.pageCount) { page ->
            val indicateColor = if (pagerState.currentPage == page) MahdELearningPlatformTheme.colors.primary else Color.Gray
            val indicatorWidth = if (pagerState.currentPage == page) 30.dp else 10.dp
//            val horizontalIndicatorPadding = if (pagerState.currentPage == page) 10.dp else 2.dp

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(indicateColor)
                    .border(
                        1.dp,
                        color = MahdELearningPlatformTheme.colors.primary,
                        shape = CircleShape
                    )
                    .size(width = indicatorWidth, height = 10.dp),
                contentAlignment = Alignment.Center
//                    .padding(horizontal = padding)
            )
            {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(
                        visible = pagerState.currentPage == page
                    )
                    {
                        Icon(
                            contentDescription = "Icon",
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            tint = Color.White,
//                            modifier = Modifier.clickable {
//                                CoroutineScope(Dispatchers.IO).launch {
//                                    pagerState.scrollToPage(page + 1)
//                                }
//                            }
                        )
                    }

                }


            }

        }


    }

}