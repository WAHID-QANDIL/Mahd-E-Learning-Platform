package org.mahd_e_learning_platform.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme

@Composable
fun AppBottomHomeNavBar(modifier: Modifier = Modifier) {
    MahdELearningPlatformTheme {
        var selected by rememberSaveable { mutableIntStateOf(0) }
        Row(modifier = modifier.background(MahdELearningPlatformTheme.colors.background)) {
            NavigationBar(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MahdELearningPlatformTheme.dimin.mediumPadding),
                containerColor = MahdELearningPlatformTheme.colors.background
            ) {
                listOf(
                    AppBottomNavItem.Home,
                    AppBottomNavItem.Search,
                    AppBottomNavItem.Courses,
                    AppBottomNavItem.Profile,
                ).forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = item.title,
                                tint = if (selected == index) MahdELearningPlatformTheme.colors.primary
                                else MahdELearningPlatformTheme.colors.black
                            )
                        },
                        label = { Text(item.title) },
                        selected = selected == index,
                        onClick = { selected = index }
                    )
                }
            }
        }

    }

}