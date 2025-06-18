package org.mahd_e_learning_platform.presentation.screens.course_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: CourseDetailsViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.course_details)) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.onToggleFavorite() }) {
                        Icon(
                            imageVector = if (uiState.isFavorited) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (uiState.isFavorited) MahdELearningPlatformTheme.colors.error else LocalContentColor.current
                        )
                    }
                }
            )
        },
        bottomBar = {
            EnrollmentSection(
                pricing = uiState.pricing,
                onEnrollNow = { viewModel.onEnrollNow() }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item { CourseHeader(uiState) }
            item { CourseInfoSection(uiState) }
            item {
                InstructorCard(
                    instructor = uiState.instructor,
                    onToggleFollow = { viewModel.onToggleFollowInstructor() }
                )
            }
        }
    }
}


@Composable
private fun CourseHeader(uiState: CourseDetailsUiState) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Image(
            painter = painterResource(id = uiState.courseImage),
            contentDescription = uiState.courseTitle,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = uiState.category,
                style = MaterialTheme.typography.labelLarge,
                color = MahdELearningPlatformTheme.colors.primary
            )
            Spacer(Modifier.width(8.dp))
            Text("•", color = Color.Gray)
            Spacer(Modifier.width(8.dp))
            Icon(Icons.Filled.Star, contentDescription = "Rating", tint = Color.Yellow, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(4.dp))
            Text("${uiState.rating} (${uiState.reviewCount})", style = MaterialTheme.typography.labelLarge)
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun CourseInfoSection(uiState: CourseDetailsUiState) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = uiState.courseTitle,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = uiState.courseDescription,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoChip(Icons.Default.DateRange, "${uiState.hours} hours")
            InfoChip(Icons.Default.PlayArrow, "${uiState.lessons} lessons")
            if (uiState.hasCertificate) {
                InfoChip(Icons.Default.Star, "Certificate")
            }
        }
    }
}

@Composable
private fun InfoChip(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = MahdELearningPlatformTheme.colors.primary, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun InstructorCard(instructor: InstructorInfo, onToggleFollow: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = instructor.imageUrl),
                    contentDescription = instructor.name,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(instructor.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(instructor.title, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }
            TextButton(onClick = onToggleFollow,  colors = ButtonDefaults.textButtonColors(contentColor = MahdELearningPlatformTheme.colors.primary) // Corrected line
            ) {
                Text(if (instructor.isFollowed) "Following" else "Follow")
            }
        }
    }
}

@Composable
private fun EnrollmentSection(pricing: PricingInfo, onEnrollNow: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("$${pricing.currentPrice}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                Row {
                    Text(
                        "$${pricing.originalPrice}",
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(Modifier.width(8.dp))
                    Text(
                        "${pricing.discountPercent}% OFF",
                        color = MahdELearningPlatformTheme.colors.error,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Button(
                onClick = onEnrollNow,
                modifier = Modifier.height(50.dp) ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MahdELearningPlatformTheme.colors.primary,
                )   ) {
                Text("Enroll Now", fontSize = 16.sp, color = MahdELearningPlatformTheme.colors.white)
            }
        }
    }
}