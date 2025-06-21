package org.mahd_e_learning_platform.presentation.screens.exams.quiz_session

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun QuizSessionScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizSessionViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MahdELearningPlatformTheme.colors.primary)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            QuizTopBar(
                uiState = uiState,
                onNavigateBack = { viewModel.onNavigateBack() },
                onMoreOptionsClicked = { viewModel.onMoreOptionsClicked() }
            )
            QuestionCard(
                uiState = uiState,
                onOptionSelected = { viewModel.onOptionSelected(it) },
                onSkip = { viewModel.onSkipQuestion() },
                onNext = { viewModel.onNextQuestion() }
            )
        }
    }
}

@Composable
private fun QuizTopBar(
    uiState: QuizSessionUiState,
    onNavigateBack: () -> Unit,
    onMoreOptionsClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                uiState.quizTitle,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onMoreOptionsClicked) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options", tint = Color.White)
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            "Question ${uiState.currentQuestionNumber} of ${uiState.totalQuestions}",
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { uiState.questionProgress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color.White,
            trackColor = Color.White.copy(alpha = 0.3f),
        )
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Timer, contentDescription = "Time left", tint = Color.White)
            Spacer(Modifier.width(4.dp))
            val minutes = uiState.remainingTimeSeconds / 60
            val seconds = uiState.remainingTimeSeconds % 60
            Text(
                String.format("%02d:%02d", minutes, seconds),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun QuestionCard(
    uiState: QuizSessionUiState,
    onOptionSelected: (String) -> Unit,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp)
        ) {
            item {
                Text(
                    uiState.currentQuestion.questionType,
                    style = MaterialTheme.typography.labelLarge,
                    color = MahdELearningPlatformTheme.colors.primary
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    uiState.currentQuestion.questionText,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(24.dp))
            }
            items(uiState.currentQuestion.options) { option ->
                AnswerOptionItem(
                    option = option,
                    isSelected = uiState.selectedOptionId == option.id,
                    onOptionSelected = { onOptionSelected(option.id) }
                )
                Spacer(Modifier.height(16.dp))
            }
            item {
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = onSkip,
                        modifier = Modifier.weight(1f),
                        border = BorderStroke(1.dp, MahdELearningPlatformTheme.colors.primary)
                    ) {
                        Text(
                            "Skip",
                            color = MahdELearningPlatformTheme.colors.primary
                        )
                    }
                    Button(
                        onClick = onNext,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MahdELearningPlatformTheme.colors.primary)
                    ) {
                        Text(
                            "Next Question",
                            color = MahdELearningPlatformTheme.colors.white
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AnswerOptionItem(
    option: AnswerOption,
    isSelected: Boolean,
    onOptionSelected: () -> Unit
) {
    val border = if (isSelected) BorderStroke(2.dp, MahdELearningPlatformTheme.colors.primary) else BorderStroke(1.dp, Color.LightGray)
    val backgroundColor = if (isSelected) MahdELearningPlatformTheme.colors.primary.copy(alpha = 0.1f) else Color.Transparent

    OutlinedButton(
        onClick = onOptionSelected,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = border,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) MahdELearningPlatformTheme.colors.primary else Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(option.id, color = if (isSelected) Color.White else Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(16.dp))
            Text(option.text, style = MaterialTheme.typography.bodyLarge, color = Color.Black, modifier = Modifier.weight(1f))
        }
    }
}