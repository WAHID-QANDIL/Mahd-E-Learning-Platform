package org.mahd_e_learning_platform.presentation.common.error

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mahd_e_learning_platform.R

class ErrorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ErrorUiState?>(null)
    val uiState = _uiState.asStateFlow()

    init {
        // For demonstration, we'll show the Server Error by default.
        // In a real app, this would be triggered by a specific error.
        showError(ErrorType.SERVER_ERROR)
    }

    // This function would be called from your app's navigation logic or network layer
    fun showError(type: ErrorType) {
        viewModelScope.launch {
            _uiState.update { createErrorState(type) }
        }
    }

    private fun createErrorState(type: ErrorType): ErrorUiState {
        return when (type) {
            ErrorType.PAGE_NOT_FOUND -> ErrorUiState(
                errorTitle = "Page Not Found",
                errorMessage = "The page you're looking for doesn't exist or has been moved.",
                errorIcon = R.drawable.ic_page_not_found,
                actions = listOf(
                    ErrorAction("Go Home", ErrorActionType.PRIMARY, ActionKey.GO_HOME)
                )
            )
            ErrorType.NO_INTERNET -> ErrorUiState(
                errorTitle = "No Internet Connection",
                errorMessage = "Please check your internet connection and try again.",
                errorIcon = R.drawable.ic_no_internet,
                actions = listOf(
                    ErrorAction("Retry Connection", ErrorActionType.PRIMARY, ActionKey.RETRY_CONNECTION)
                )
            )
            ErrorType.SERVER_ERROR -> ErrorUiState(
                errorTitle = "Server Error",
                errorMessage = "We're experiencing technical difficulties. Our team has been notified and is working to resolve this issue.",
                errorIcon = R.drawable.ic_server_error,
                errorCode = 500,
                errorStatus = "Internal Server Error",
                actions = listOf(
                    ErrorAction("Try Again", ErrorActionType.PRIMARY, ActionKey.TRY_AGAIN),
                    ErrorAction("Go Back", ErrorActionType.SECONDARY, ActionKey.GO_BACK)
                )
            )
            ErrorType.REQUEST_TIMEOUT -> ErrorUiState(
                errorTitle = "Request Timeout",
                errorMessage = "The request took too long to complete. Please try again.",
                errorIcon = R.drawable.ic_request_timeout,
                actions = listOf(
                    ErrorAction("Try Again", ErrorActionType.PRIMARY, ActionKey.TRY_AGAIN)
                )
            )
        }
    }
}

enum class ErrorType {
    PAGE_NOT_FOUND,
    NO_INTERNET,
    SERVER_ERROR,
    REQUEST_TIMEOUT
}
enum class ActionKey {
    TRY_AGAIN,
    RETRY_CONNECTION,
    GO_BACK,
    GO_HOME,
    CONTACT_SUPPORT
}