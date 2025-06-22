package org.mahd_e_learning_platform.presentation.screens.course_player

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursePlayerScreen(
    modifier: Modifier = Modifier,
    viewModel: CoursePlayerViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.courseTitle, style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.onMoreOptionsSelected() }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                    }
                }
            )
        },
        bottomBar = {
            PlayerControls(
                onPrevious = { viewModel.onPreviousLesson() },
                onNext = { viewModel.onNextLesson() }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item { VideoPlayerSection(onPlayPause = { viewModel.onPlayPause() }) }
            item { LessonDetailsSection(uiState.currentLesson, onToggleBookmark = { viewModel.onToggleBookmark() }) }
            item { CourseProgressSection(uiState.courseProgress) }
            item { CourseContentHeader() }
            items(uiState.lessons.size) { index ->
                LessonItemRow(lesson = uiState.lessons[index])
            }
        }
    }
}

@Composable
private fun VideoPlayerSection(onPlayPause: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // This is a placeholder for a real video player
        IconButton(onClick = onPlayPause) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play/Pause",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )
        }
        Text(
            "2:45",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
        Slider(
            value = 0.2f,
            onValueChange = {},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 60.dp, vertical = 4.dp),
            colors = SliderDefaults.colors(
                thumbColor = MahdELearningPlatformTheme.colors.primary,
                activeTrackColor = MahdELearningPlatformTheme.colors.primary
                // You can also customize inactiveTrackColor and activeTickColor if needed
                // inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                // activeTickColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        Text(
            "15:30",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp)
        )
    }
}

@Composable
private fun LessonDetailsSection(details: LessonDetails, onToggleBookmark: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(details.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            IconButton(onClick = onToggleBookmark) {
                Icon(
                    painter = if (details.isBookmarked) painterResource(id = R.drawable.bookmark) else painterResource(id = R.drawable.bookmarkborder), // Use your actual drawable names
                    contentDescription = "Bookmark",
                    tint = if (details.isBookmarked) MahdELearningPlatformTheme.colors.primary else Color.Gray
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${details.durationInMin} min", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Spacer(Modifier.width(8.dp))
            Text("•", color = Color.Gray)
            Spacer(Modifier.width(8.dp))
            Text("${details.viewCount} views", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
        Spacer(Modifier.height(12.dp))
        Text(details.description, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray, lineHeight = 22.sp)
    }
}

@Composable
private fun CourseProgressSection(progress: CourseProgress) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Course Progress", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "${progress.completedLessons} of ${progress.totalLessons} completed",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
        Spacer(Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress.progress },
            modifier = Modifier.fillMaxWidth(),
            color = MahdELearningPlatformTheme.colors.primary, // Set the progress bar color
            trackColor = MahdELearningPlatformTheme.colors.primary.copy(alpha = 0.3f) // Optional: Set the track color
        )
    }
}

@Composable
private fun CourseContentHeader() {
    Text(
        "Course Content",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
private fun LessonItemRow(lesson: LessonItem) {
    val backgroundColor = if (lesson.status == LessonStatus.WATCHING) MahdELearningPlatformTheme.colors.primary.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if (lesson.status == LessonStatus.WATCHING) MahdELearningPlatformTheme.colors.primary else Color.Gray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable(enabled = lesson.status != LessonStatus.LOCKED) {}
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(if (lesson.status != LessonStatus.LOCKED) contentColor.copy(alpha = 0.2f) else Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (lesson.status == LessonStatus.WATCHING) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "Watching", tint = contentColor, modifier = Modifier.size(20.dp))
            } else {
                Text(lesson.id.toString(), color = if (lesson.status != LessonStatus.LOCKED) contentColor else Color.DarkGray, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(lesson.title, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Text("${lesson.durationInMin} min" + if (lesson.status == LessonStatus.WATCHING) " • Currently watching" else "", style = MaterialTheme.typography.bodySmall, color = contentColor)
        }
        Spacer(Modifier.width(16.dp))
        when (lesson.status) {
            LessonStatus.WATCHING -> Icon(Icons.Filled.CheckCircle, contentDescription = "Completed", tint = contentColor)
            LessonStatus.LOCKED -> Icon(Icons.Filled.Lock, contentDescription = "Locked", tint = Color.LightGray)
            else -> {}
        }
    }
}

@Composable
private fun PlayerControls(onPrevious: () -> Unit, onNext: () -> Unit) {
    Surface(shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onPrevious,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MahdELearningPlatformTheme.colors.primary // Color for text and icon
                ),
                border = BorderStroke(1.dp, MahdELearningPlatformTheme.colors.primary) // Color for the outline
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                Spacer(Modifier.width(4.dp))
                Text("Previous")
            }
            Button(
                onClick = onNext,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MahdELearningPlatformTheme.colors.primary, // Background color
                    contentColor = Color.White // Text and icon color on top of the primary background
                )
            ) {
                Text("Next")
                Spacer(Modifier.width(4.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
            }
        }
    }
}