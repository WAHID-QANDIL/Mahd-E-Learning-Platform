package org.mahd_e_learning_platform.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mahd_e_learning_platform.presentation.screens.home.component.CategoriesSection
import org.mahd_e_learning_platform.presentation.screens.home.component.HomeCurrentCourse
import org.mahd_e_learning_platform.presentation.screens.home.component.HomeTopAppBar
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            avatarUrl = "https://picsum.photos/200",
            onSearch = { },
            onClickNotifications = { },
            onAvatarClicked = { },
            studentName = "Micheal"
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
        HomeCurrentCourse(
            modifier = Modifier.fillMaxWidth(),
            onPlay = {},
            progress = 30f,
            courseName = "Android Programming"
        )
        CategoriesSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MahdELearningPlatformTheme.dimin.largePadding),
            categories = 4,
            onProgrammingClick = { },
            onDesignClick = { },
            onBusinessClick = { },
            onPhotographyClick = { },
            programmingCourses = 109,
            designCourses = 3,
            businessCourses = 33,
            photographyCourses = 44
        )


    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}