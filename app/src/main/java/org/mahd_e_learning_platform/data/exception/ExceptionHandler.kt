package org.mahd_e_learning_platform.data.exception

sealed class ExceptionHandler(message: String) : Exception(message) {
    class UnauthorizedException :
        ExceptionHandler(message = "Invalid credentials. Please check your email or password.")

    class ServerErrorException :
        ExceptionHandler(message = "Server is unavailable, please, try again later")

    class UnknownException :
        ExceptionHandler(message = "Unknown error occurred")


}