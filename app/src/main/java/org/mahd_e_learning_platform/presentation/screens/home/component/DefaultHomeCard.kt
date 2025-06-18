package org.mahd_e_learning_platform.presentation.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

//This card will be showing if the user not yet enrolled in any courses


@Composable
fun DefaultHomeCard(
    modifier: Modifier = Modifier,
    onViewCourses: () -> Unit,
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

            ElevatedCard(
                modifier = Modifier
                    .padding(MahdELearningPlatformTheme.dimin.mediumPadding)
                    .clickable(onClick = onViewCourses),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MahdELearningPlatformTheme.colors.white
                ),
                shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.smallPadding))
            ) {
                Text(
                    text = stringResource(R.string.enroll_now),
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.black,
                    modifier = Modifier.padding(MahdELearningPlatformTheme.dimin.smallPadding),
                )
            }
        }
    }
}

@Preview
@Composable
private fun DefaultHomeCardPreview() {
    DefaultHomeCard { }
}

