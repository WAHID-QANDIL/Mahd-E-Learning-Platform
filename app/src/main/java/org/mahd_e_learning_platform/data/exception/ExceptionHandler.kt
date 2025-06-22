package org.mahd_e_learning_platform.data.exception

sealed class ExceptionHandler(message: String) : Exception(message) {
    class UnauthorizedException :
        ExceptionHandler(message = "Invalid credentials. Please check your email or password.")

    // In your ExceptionHandler.kt or wherever ServerErrorExcepiton is defined
    class ServerErrorException(message: String? = null) : Exception(message) {

    }
    class UnknownException :
        ExceptionHandler(message = "Unknown error occurred")


}