package org.mahd_e_learning_platform.presentation.screens.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.domain.model.Course
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun RecommendedCoursesSection(
    modifier: Modifier = Modifier,
    courses: List<Course>,
    onViewAll:()-> Unit,
    onCourseClicked:(String)-> Unit
    ) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.recommended_courses),
                style = MahdELearningPlatformTheme.typography.titleLarge,
                color = MahdELearningPlatformTheme.colors.black
            )
            Text(
                text = stringResource(R.string.view_all),
                style = MahdELearningPlatformTheme.typography.titleMedium,
                color = MahdELearningPlatformTheme.colors.primary,
                modifier = Modifier.clickable(onClick = onViewAll)
            )

        }
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

        LazyColumn (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){

            items(count = courses.size) {  index->
                HomeCourseCard(
                    course = courses[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { onCourseClicked(courses[index].courseTitle) })
                )
                Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
            }
        }


    }



}