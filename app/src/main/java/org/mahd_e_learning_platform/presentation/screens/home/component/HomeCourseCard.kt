package org.mahd_e_learning_platform.presentation.screens.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.domain.model.Course
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
import org.mahd_e_learning_platform.utils.homeCourseImageCardSize

@Composable
fun HomeCourseCard(
    modifier: Modifier = Modifier,
    course: Course
    ) {


    MahdELearningPlatformTheme {
        ElevatedCard(
            modifier = modifier,
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = MahdELearningPlatformTheme.dimin.smallPadding
            )
        ) {

            Row(
                modifier = Modifier.fillMaxSize().padding(MahdELearningPlatformTheme.dimin.mediumPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {

                Box(
                    modifier = Modifier
                        .size(
                            homeCourseImageCardSize.first,
                            homeCourseImageCardSize.second
                        )
                        .clip(
                            RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding))
                        ),
                    contentAlignment = Alignment.Center,

                    ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(course.courseImageUrl)
                            .build(),
                        contentDescription = stringResource(R.string.course_image),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.app_logo)
                    )
                }

                Spacer(Modifier.width(MahdELearningPlatformTheme.dimin.smallPadding))

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = course.courseTitle,
                        style = MahdELearningPlatformTheme.typography.titleLarge,
                        color = MahdELearningPlatformTheme.colors.black,
                    )
                    Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.extraSmallPadding))
                    Text(
                        text =course.courseDescription,
                        style = MahdELearningPlatformTheme.typography.bodyMedium,
                        color = MahdELearningPlatformTheme.colors.subText,
                    )
                    Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = stringResource(R.string.star),
                                tint = MahdELearningPlatformTheme.colors.yalow
                            )
                            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.extraSmallPadding))

                            Text(
                                text = "${course.rate}",
                                style = MahdELearningPlatformTheme.typography.bodyMedium,
                                color = MahdELearningPlatformTheme.colors.subText
                            )
                            Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.extraSmallPadding))

                            Text(
                                text = "(${course.enrollments})k",
                                style = MahdELearningPlatformTheme.typography.bodyMedium,
                                color = MahdELearningPlatformTheme.colors.subText
                            )

                        }
                        Text(
                            text = "$${course.cost}",
                            style = MahdELearningPlatformTheme.typography.bodyLarge,
                            color = MahdELearningPlatformTheme.colors.primary
                        )

                    }

                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeCourseCardPreview() {
    HomeCourseCard(
        course = Course(
            courseImageUrl = "",
            courseTitle = "Complete React Course",
            courseDescription = "Build modern web applications",
            enrollments = 2,
            rate = 3.4f,
            cost = 33,
            courseId = TODO(),
            progress = TODO(),
            educator = TODO(),
            level = TODO(),
            sections = TODO(),
            category = TODO(),
        ),
    )

}