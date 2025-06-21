package org.mahd_e_learning_platform.presentation.screens.auth.verification

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.mahd_e_learning_platform.R
import org.mahd_e_learning_platform.ui.theme.MahdELearningPlatformTheme
import java.util.concurrent.TimeUnit

@Composable
fun VerificationScreen(
    modifier: Modifier = Modifier,
    viewModel: VerificationViewModel,
    onBackClicked: () -> Unit,
    onVerifyCode: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MahdELearningPlatformTheme.dimin.largePadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }

        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = stringResource(R.string.verification),
            modifier = Modifier.size(80.dp),
            tint = MahdELearningPlatformTheme.colors.primary
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

        Text(
            text = stringResource(R.string.enter_verification_code),
            style = MahdELearningPlatformTheme.typography.titleLarge
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.smallPadding))
        Text(
            text = stringResource(R.string.verification_code_subtitle),
            style = MahdELearningPlatformTheme.typography.bodyLarge,
            color = MahdELearningPlatformTheme.colors.subText,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.extraSmallPadding))

        OtpTextField(
            otpText = uiState.otpCode,
            onOtpTextChange = { viewModel.onOtpCodeChanged(it) }
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))

        // Timer display
        val minutes = TimeUnit.SECONDS.toMinutes(uiState.remainingSeconds.toLong())
        val seconds = uiState.remainingSeconds % 60
        Text(
            text = stringResource(R.string.code_expires_in, String.format("%02d:%02d", minutes, seconds)),
            style = MahdELearningPlatformTheme.typography.bodyLarge,
            color = MahdELearningPlatformTheme.colors.primary
        )
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.largePadding))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.didnt_receive_code),
                style = MahdELearningPlatformTheme.typography.bodyMedium,
                color = MahdELearningPlatformTheme.colors.subText
            )
            Spacer(Modifier.width(MahdELearningPlatformTheme.dimin.smallPadding))
            TextButton(
                onClick = { viewModel.onResendCode() },
                enabled = uiState.isResendEnabled
            ) {
                Text(text = stringResource(R.string.resend_code))
            }
        }
        Spacer(Modifier.height(MahdELearningPlatformTheme.dimin.mediumPadding))


        Button(
            onClick = onVerifyCode,
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.otpCode.length == 6,
            shape = RoundedCornerShape(corner = CornerSize(MahdELearningPlatformTheme.dimin.smallPadding)),
            colors = ButtonDefaults.buttonColors(containerColor = MahdELearningPlatformTheme.colors.primary)
        ) {
            Text(
                text = stringResource(R.string.verify_code),
                style = MahdELearningPlatformTheme.typography.titleLarge,
                color = MahdELearningPlatformTheme.colors.white
            )
        }

        Spacer(Modifier.weight(1f))

        Row {
            Text(
                text = stringResource(R.string.having_trouble),
                style = MahdELearningPlatformTheme.typography.bodyMedium,
                color = MahdELearningPlatformTheme.colors.subText,
            )
            Text(
                text = stringResource(R.string.contact_support),
                modifier = Modifier.clickable { /* TODO */ },
                style = MahdELearningPlatformTheme.typography.bodyMedium,
                color = MahdELearningPlatformTheme.colors.primary,
            )
        }
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange(it.text)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    val char = when {
                        index >= otpText.length -> ""
                        else -> otpText[index].toString()
                    }
                    val isFocused = otpText.length == index
                    Text(
                        modifier = Modifier
                            .width(40.dp)
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) MahdELearningPlatformTheme.colors.primary else MahdELearningPlatformTheme.colors.subText,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(2.dp),
                        text = char,
                        style = MahdELearningPlatformTheme.typography.headlineMedium,
                        color = MahdELearningPlatformTheme.colors.black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}