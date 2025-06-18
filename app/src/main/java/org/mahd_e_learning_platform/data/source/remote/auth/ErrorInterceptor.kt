package org.mahd_e_learning_platform.data.source.remote.auth

import okhttp3.Interceptor
import okhttp3.Response
import org.mahd_e_learning_platform.data.exception.ExceptionHandler

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            throw when (response.code) {
                401 -> ExceptionHandler.UnauthorizedException()
                in 500..599 -> ExceptionHandler.ServerErrorException()
                else -> ExceptionHandler.UnknownException()
            }
        }
        return response
    }

}
