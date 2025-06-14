package org.mahd_e_learning_platform.presentation.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun EnrollmentSuccessScreen(
    modifier: Modifier = Modifier,
    viewModel: EnrollmentSuccessViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    EnrollmentSuccessContent(
        modifier = modifier,
        courseInfo = uiState.courseInfo,
        onStartLearningNow = { viewModel.onStartLearningNow() },
        onViewCourseDetails = { viewModel.onViewCourseDetails() },
        onContactSupport = { viewModel.onContactSupport() }
    )
}

@Composable
fun EnrollmentSuccessContent(
    modifier: Modifier = Modifier,
    courseInfo: CourseInfo,
    onStartLearningNow: () -> Unit,
    onViewCourseDetails: () -> Unit,
    onContactSupport: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MahdELearningPlatformTheme.dimin.largePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))
        Icon(
            imageVector = Icons.Filled.CheckCircle,
            contentDescription = stringResource(R.string.enrollment_successful),
            modifier = Modifier.size(80.dp),
            tint = MahdELearningPlatformTheme.colors.primary
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

        Text(
            text = stringResource(R.string.enrollment_successful),
            style = MahdELearningPlatformTheme.typography.bodyLarge,
            color = MahdELearningPlatformTheme.colors.black
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

        Text(
            text = stringResource(R.string.enrollment_subtitle),
            style = MahdELearningPlatformTheme.typography.bodyLarge,
            color = MahdELearningPlatformTheme.colors.subText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = MahdELearningPlatformTheme.dimin.mediumPadding)
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

        CourseInfoCard(courseInfo = courseInfo)

        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

        Button(
            onClick = onStartLearningNow,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(MahdELearningPlatformTheme.dimin.smallPadding)),
            colors = ButtonDefaults.buttonColors(containerColor = MahdELearningPlatformTheme.colors.primary)
        ) {
            Text(stringResource(R.string.start_learning_now))
        }
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

        OutlinedButton(
            onClick = onViewCourseDetails,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(MahdELearningPlatformTheme.dimin.smallPadding)),
        ) {
            Text(stringResource(R.string.view_course_details))
        }

        Spacer(Modifier.weight(1f))

        TextButton(onClick = onContactSupport) {
            Text(stringResource(R.string.need_help_contact_support))
        }
    }
}

@Composable
fun CourseInfoCard(
    modifier: Modifier = Modifier,
    courseInfo: CourseInfo
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding)),
    ) {
        Row(
            modifier = Modifier.padding(MahdELearningPlatformTheme.dimin.mediumPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = courseInfo.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MahdELearningPlatformTheme.colors.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.smallPadding))
                    )
                    .padding(MahdELearningPlatformTheme.dimin.smallPadding),
                tint = MahdELearningPlatformTheme.colors.primary
            )
            Spacer(Modifier.width(MahdELearningPlatformTheme.dimin.mediumPadding))
            Column {
                Text(
                    text = courseInfo.title,
                    style = MahdELearningPlatformTheme.typography.titleMedium
                )
                Text(
                    text = "${courseInfo.duration} • ${courseInfo.level}",
                    style = MahdELearningPlatformTheme.typography.bodyMedium,
                    color = MahdELearningPlatformTheme.colors.subText
                )
            }
        }
    }
}