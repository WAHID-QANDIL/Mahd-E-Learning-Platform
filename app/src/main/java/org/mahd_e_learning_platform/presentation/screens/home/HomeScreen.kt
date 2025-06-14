package org.mahd_e_learning_platform.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.domain.model.Course
import org.mahd_e_learning_platform.presentation.screens.home.component.BottomHomeNavBar
import org.mahd_e_learning_platform.presentation.screens.home.component.CategoriesSection
import org.mahd_e_learning_platform.presentation.screens.home.component.DefaultHomeCard
import org.mahd_e_learning_platform.presentation.screens.home.component.HomeCurrentCourse
import org.mahd_e_learning_platform.presentation.screens.home.component.HomeTopAppBar
import org.mahd_e_learning_platform.presentation.screens.home.component.RecommendedCoursesSection
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
import org.mahd_e_learning_platform.utils.homeBottomNavBarHeight
import org.mahd_e_learning_platform.utils.recommendedCoursesSectionHeight

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle()
    val student = uiState.value.student
    val currentCourse = uiState.value.course
    val recommendedCourses = uiState.value.recommendedCourses
    val categoriesUiState = uiState.value.categoriesUiState

    MahdELearningPlatformTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = modifier.navigationBarsPadding(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                stickyHeader {
                    HomeTopAppBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MahdELearningPlatformTheme.colors.background)
                            .padding(MahdELearningPlatformTheme.dimin.mediumPadding)
                            .clip(
                                RoundedCornerShape(CornerSize(MahdELearningPlatformTheme.dimin.mediumPadding))
                            ),
                        avatarUrl = "https://picsum.photos/200",
                        onSearch = { },
                        onClickNotifications = { },
                        onAvatarClicked = { },
                        studentName = student?.Fname ?: ""
                    )
                }

                item {
                    Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))
                }
                item {
                    currentCourse?.let {
                        HomeCurrentCourse(
                            modifier = Modifier.fillMaxWidth(),
                            onPlay = {},
                            progress = currentCourse.progress,
                            courseName = currentCourse.courseTitle
                        )
                    } ?: DefaultHomeCard(
                        modifier = Modifier.fillMaxWidth(),
                        onViewCourses = {},
                    )

                }

                item {
                    categoriesUiState?.let {
                        CategoriesSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MahdELearningPlatformTheme.dimin.largePadding),
                            categories = 4,
                            onProgrammingClick = { },
                            onDesignClick = { },
                            onBusinessClick = { },
                            onPhotographyClick = { },
                            programmingCourses = categoriesUiState.programming,
                            designCourses = categoriesUiState.design,
                            businessCourses = categoriesUiState.business,
                            photographyCourses = categoriesUiState.photography
                        )
                    }

                }
                item {
                    RecommendedCoursesSection(
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.9f)
                            .height(
                                recommendedCoursesSectionHeight
                            ),
                        courses = recommendedCourses ?: listOf(
                            //TODO: delete these dummy courses once we have real data
                            Course(
                                courseImageUrl = "https://picsum.photos/200",
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
                            Course(
                                courseImageUrl = "https://picsum.photos/200",
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
                            Course(
                                courseImageUrl = "https://picsum.photos/200",
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
                            Course(
                                courseImageUrl = "https://picsum.photos/200",
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
                        ),
                        onViewAll = { },
                        onCourseClicked = {}
                    )
                }

            }
//            BottomHomeNavBar(
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//                    .fillMaxWidth()
//                    .height(homeBottomNavBarHeight)
//            )
        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}