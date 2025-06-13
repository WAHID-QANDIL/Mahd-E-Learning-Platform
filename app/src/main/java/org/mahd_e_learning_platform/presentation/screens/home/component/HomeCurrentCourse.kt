package org.mahd_e_learning_platform.presentation.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun HomeCurrentCourse(
    modifier: Modifier = Modifier,
    onPlay: () -> Unit,
    progress: Float,
    courseName: String,
) {
    MahdELearningPlatformTheme {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MahdELearningPlatformTheme.colors.primary,
                            MahdELearningPlatformTheme.colors.white
                        )
                    )
                )
                .padding(MahdELearningPlatformTheme.dimin.mediumPadding)
        ) {
            Text(
                text = stringResource(R.string.explore_courses),
                style = MahdELearningPlatformTheme.typography.titleLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.discover_new_skills_and_advance_your_career),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MahdELearningPlatformTheme.shapes.medium),
                colors = CardDefaults.cardColors(containerColor = MahdELearningPlatformTheme.colors.background),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(MahdELearningPlatformTheme.dimin.mediumPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Continue Learning",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )

                        Text(
                            text = courseName,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))

                        LinearProgressIndicator(
                            progress = { progress % 10 + progress / 100 },
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(MahdELearningPlatformTheme.dimin.smallPadding)
                                .clip(MahdELearningPlatformTheme.shapes.medium),
                            color = MahdELearningPlatformTheme.colors.primary,
                            trackColor = MahdELearningPlatformTheme.colors.subText,
                            gapSize = 0.dp,
                            strokeCap = StrokeCap.Square,
                        )
                        Spacer(modifier = Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))

                        Text(
                            text = "$progress%",
                            style = MahdELearningPlatformTheme.typography.titleMedium,
                            color = MahdELearningPlatformTheme.colors.subText
                        )
                    }

                    Spacer(modifier = Modifier.width(MahdELearningPlatformTheme.dimin.mediumPadding))

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(color = MahdELearningPlatformTheme.colors.secondary)
                            .clickable(onClick = onPlay),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }


}


@Preview
@Composable
private fun HomeCurrentCoursePreview() {
    HomeCurrentCourse(
        onPlay = { },
        progress = 10f,
        courseName = "Android Compose"
    )
}