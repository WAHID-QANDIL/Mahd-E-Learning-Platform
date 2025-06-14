package org.mahd_e_learning_platform.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.presentation.screens.home.component.BottomHomeNavBar
import org.mahd_e_learning_platform.presentation.screens.search.components.CourseItem
import org.mahd_e_learning_platform.presentation.screens.search.components.SearchBar
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
import org.mahd_e_learning_platform.utils.homeBottomNavBarHeight

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    onCourseClick: (String) -> Unit = {},
) {
    val uiState = searchViewModel.uiState.collectAsStateWithLifecycle().value

    MahdELearningPlatformTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                    .padding(bottom = homeBottomNavBarHeight)
            ) {
                // Search Bar
                SearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = { searchViewModel.updateSearchQuery(it) },
                    onClearClick = { searchViewModel.updateSearchQuery("") }
                )

                // Content based on state
                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    uiState.error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = uiState.error,
                                style = MahdELearningPlatformTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    uiState.courses.isEmpty() && uiState.searchQuery.isNotEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No courses found for '${uiState.searchQuery}'",
                                style = MahdELearningPlatformTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    uiState.searchQuery.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Search for courses to get started",
                                style = MahdELearningPlatformTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    else -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(uiState.courses.size) { course ->
                                val currentCourse = uiState.courses[course]
                                CourseItem(
                                    course = currentCourse,
                                    onCourseClick = { onCourseClick(currentCourse.courseId) }
                                )
                            }
                        }
                    }
                }
            }

            // Bottom Navigation Bar
            BottomHomeNavBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(homeBottomNavBarHeight)
            )
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen()
}