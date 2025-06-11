package org.mahd_e_learning_platform.presentation.screens.home.component

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesSection(
    modifier: Modifier = Modifier,
    categories: Int,
    onProgrammingClick: () -> Unit,
    onDesignClick: () -> Unit,
    onBusinessClick: () -> Unit,
    onPhotographyClick: () -> Unit,
    programmingCourses: Int,
    designCourses: Int,
    businessCourses: Int,
    photographyCourses: Int,
) {


    val carouselState = rememberCarouselState(itemCount = { categories })
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.categories),
            style = MahdELearningPlatformTheme.typography.titleLarge,
            color = MahdELearningPlatformTheme.colors.black
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

        HorizontalMultiBrowseCarousel(
            state = carouselState,
            preferredItemWidth = 210.dp,
            itemSpacing = 5.dp,
            flingBehavior = CarouselDefaults.singleAdvanceFlingBehavior(state = carouselState),
            contentPadding = PaddingValues(MahdELearningPlatformTheme.dimin.smallPadding)
        ) { index ->
            when (index) {
                0 -> {
                    CategoriesSectionCard(
                        icon = Icons.Default.Build,
                        title = stringResource(R.string.programming),
                        numberOfCourses = programmingCourses,
                        onClick = onProgrammingClick,
                        color = MahdELearningPlatformTheme.colors.blue
                    )
                }

                1 -> {
                    CategoriesSectionCard(
                        icon = Icons.Default.Star,
                        title = stringResource(R.string.design),
                        numberOfCourses = designCourses,
                        onClick = onDesignClick,
                        color = MahdELearningPlatformTheme.colors.purple
                    )
                }

                2 -> {
                    CategoriesSectionCard(
                        icon = Icons.Default.ShoppingCart,
                        title = stringResource(R.string.business),
                        numberOfCourses = businessCourses,
                        onClick = onBusinessClick,
                        color = MahdELearningPlatformTheme.colors.green
                    )
                }

                3 -> {
                    CategoriesSectionCard(
                        icon = Icons.Default.ThumbUp,
                        title = stringResource(R.string.photography),
                        numberOfCourses = photographyCourses,
                        onClick = onPhotographyClick,
                        color = MahdELearningPlatformTheme.colors.lightRed
                    )
                }
            }

        }
    }
}

@Composable
fun CategoriesSectionCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    numberOfCourses: Int,
    onClick: () -> Unit,
    color: Color,
) {

    MahdELearningPlatformTheme {
        OutlinedCard(
            modifier = modifier,
            onClick = onClick,
            shape = MahdELearningPlatformTheme.shapes.medium,
            elevation = CardDefaults.outlinedCardElevation(
                defaultElevation = MahdELearningPlatformTheme.dimin.smallPadding
            ),
            colors = CardDefaults.outlinedCardColors(
                containerColor = color
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color
                )
                Spacer(Modifier.padding(vertical = MahdELearningPlatformTheme.dimin.smallPadding))

                Text(
                    text = title,
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.black
                )
                Spacer(Modifier.padding(vertical = MahdELearningPlatformTheme.dimin.smallPadding))

                Text(
                    text = "$numberOfCourses Courses",
                    style = MahdELearningPlatformTheme.typography.titleMedium,
                    color = MahdELearningPlatformTheme.colors.subText
                )


            }


        }
    }


}

@Preview(showBackground = true)
@Composable
private fun CategoriesSectionCardPreview() {

    CategoriesSectionCard(
        modifier = Modifier,
        icon = Icons.Default.Email,
        title = "Programming",
        numberOfCourses = 29,
        onClick = { },
        color = MahdELearningPlatformTheme.colors.green
    )
}