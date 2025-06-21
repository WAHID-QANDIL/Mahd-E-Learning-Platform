package org.mahd_e_learning_platform.presentation.screens.exams.my_exams

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.QueryBuilder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyExamsScreen(
    modifier: Modifier = Modifier,
    viewModel: MyExamsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Exams") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onNavigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.onSearchClicked() }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FilterChipGroup(
                selectedFilter = uiState.selectedFilter,
                onFilterChange = { viewModel.onFilterChange(it) }
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.filteredExams) { exam ->
                    ExamCard(
                        exam = exam,
                        onStartExam = { viewModel.onStartExam(exam.id) },
                        onViewResults = { viewModel.onViewResults(exam.id) }
                    )
                }
            }
        }
    }
}


@Composable
private fun FilterChipGroup(
    selectedFilter: ExamFilter,
    onFilterChange: (ExamFilter) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ExamFilter.values().forEach { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { onFilterChange(filter) },
                label = { Text(filter.name) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MahdELearningPlatformTheme.colors.primary,
                    selectedLabelColor = MahdELearningPlatformTheme.colors.white,
                )
            )
        }
    }
}

@Composable
private fun ExamCard(
    exam: ExamItem,
    onStartExam: () -> Unit,
    onViewResults: () -> Unit,
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(exam.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(exam.subject, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
                StatusTag(status = exam.status)
            }
            Spacer(Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoChip(icon = Icons.Default.QueryBuilder, text = "${exam.durationInMin} min")
                InfoChip(icon = Icons.AutoMirrored.Filled.HelpOutline, text = "${exam.questionCount} questions")
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            ExamCardActionSection(
                exam = exam,
                onStartExam = onStartExam,
                onViewResults = onViewResults
            )
        }
    }
}

@Composable
private fun StatusTag(status: ExamStatus) {
    val (text, color) = when (status) {
        ExamStatus.LIVE -> "Live" to MahdELearningPlatformTheme.colors.primary
        ExamStatus.UPCOMING -> "Upcoming" to Color(0xFFFFA500) // Orange
        ExamStatus.COMPLETED -> "Completed" to Color.Gray
    }
    Text(
        text = text,
        color = color,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
private fun InfoChip(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(4.dp))
        Text(text, color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
private fun ExamCardActionSection(
    exam: ExamItem,
    onStartExam: () -> Unit,
    onViewResults: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            val label = when(exam.status) {
                ExamStatus.LIVE -> "Due:"
                ExamStatus.UPCOMING -> "Starts:"
                ExamStatus.COMPLETED -> "Score:"
            }
            val value = when(exam.status) {
                ExamStatus.COMPLETED -> "${exam.score}%"
                else -> exam.scheduleInfo.substringAfter(":")
            }
            Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            if (exam.status == ExamStatus.COMPLETED) {
                Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = MahdELearningPlatformTheme.colors.primary)
            } else {
                Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
        }

        when (exam.status) {
            ExamStatus.LIVE -> Button(
                onClick = onStartExam,
                colors = ButtonDefaults.buttonColors(containerColor = MahdELearningPlatformTheme.colors.primary)
            ) { Text("Start Exam") }
            ExamStatus.UPCOMING -> OutlinedButton(
                onClick = { /* Could open calendar */ },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MahdELearningPlatformTheme.colors.primary),
                border = BorderStroke(1.dp, MahdELearningPlatformTheme.colors.primary)
            ) { Text("Schedule") }
            ExamStatus.COMPLETED -> OutlinedButton(
                onClick = onViewResults,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MahdELearningPlatformTheme.colors.primary),
                border = BorderStroke(1.dp, MahdELearningPlatformTheme.colors.primary)
            ) { Text("View Results") }
        }
    }
}