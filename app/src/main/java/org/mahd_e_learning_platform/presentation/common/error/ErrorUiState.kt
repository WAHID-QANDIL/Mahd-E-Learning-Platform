package org.mahd_e_learning_platform.presentation.common.error

// Defines the visual style of an action button
enum class ErrorActionType {
    PRIMARY, // A filled button
    SECONDARY // An outlined button
}

// Represents a single action button on the error screen
data class ErrorAction(
    val text: String,
    val type: ErrorActionType,
    val key: String // A unique key to identify the action in the ViewModel
)

// The enhanced, generic state for the error screen
data class ErrorUiState(
    val errorTitle: String,
    val errorMessage: String,
    val errorIcon: Int,
    val actions: List<ErrorAction>,
    val errorCode: Int? = null,
    val errorStatus: String? = null
)