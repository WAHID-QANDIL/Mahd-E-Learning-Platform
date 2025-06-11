package org.mahd_e_learning_platform.presentation.screens.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme


@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    avatarUrl: String,
    onSearch: () -> Unit,
    onClickNotifications: () -> Unit,
    onAvatarClicked: () -> Unit,
    studentName: String,

    ) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.weight(2f)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(
                        color = Color.Gray,
                        shape = CircleShape
                    )
                    .clickable(onClick = onAvatarClicked)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(avatarUrl)
                        .build(),
                    contentDescription = stringResource(R.string.student_image),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.app_logo),

                    )
            }
            Spacer(modifier = Modifier.width(5.dp))

            Column {
                Text(
                    "Hi, $studentName",
                    style = MahdELearningPlatformTheme.typography.titleLarge,
                    color = MahdELearningPlatformTheme.colors.black,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(R.string.ready_to_learn),
                    style = MahdELearningPlatformTheme.typography.titleMedium,
                    color = MahdELearningPlatformTheme.colors.subText,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row(
            modifier = Modifier.weight(2f),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = Color.Gray.copy(alpha = 0.15f),
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color.Gray.copy(alpha = 0.3f)
                    )
                    .padding(vertical = 8.dp)
                    .clickable(onClick = onSearch),

                )
            {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search),
                    modifier = Modifier.padding(MahdELearningPlatformTheme.dimin.mediumPadding)
                )

            }
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        color = Color.Gray.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(MahdELearningPlatformTheme.dimin.smallPadding)
                    )
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        spotColor = Color.Gray.copy(alpha = 0.3f)
                    )
                    .padding(vertical = 8.dp)
                    .clickable(onClick = onClickNotifications)

            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = stringResource(R.string.notifications),
                    modifier = Modifier.padding(MahdELearningPlatformTheme.dimin.smallPadding)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeTopAppBarPreview() {

    HomeTopAppBar(
        modifier = Modifier.fillMaxSize(),
        avatarUrl = "https://picsum.photos/200",
        onSearch = { },
        studentName = "Sara",
        onAvatarClicked = {},
        onClickNotifications = {}
    )
}