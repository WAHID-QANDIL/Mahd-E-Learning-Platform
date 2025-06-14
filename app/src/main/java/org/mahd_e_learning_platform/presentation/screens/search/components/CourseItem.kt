package org.mahd_e_learning_platform.presentation.screens.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.mahd_e_learning_platform.domain.model.Course
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun CourseItem(
    modifier: Modifier = Modifier,
    course: Course,
    onCourseClick: (Course) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = MahdELearningPlatformTheme.dimin.mediumPadding,
                vertical = MahdELearningPlatformTheme.dimin.smallPadding
            )
            .clickable { onCourseClick(course) },
        shape = RoundedCornerShape(MahdELearningPlatformTheme.dimin.mediumPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MahdELearningPlatformTheme.colors.background
        )
    ) {
        Row(
            modifier = Modifier.padding(MahdELearningPlatformTheme.dimin.mediumPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = course.courseImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = course.courseTitle,
                    style = MahdELearningPlatformTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = course.courseDescription,
                    style = MahdELearningPlatformTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(
                        text = "$${course.cost}",
                        style = MahdELearningPlatformTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "★ ${course.rate}",
                        style = MahdELearningPlatformTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}