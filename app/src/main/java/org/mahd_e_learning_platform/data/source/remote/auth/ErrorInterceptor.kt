package org.mahd_e_learning_platform.data.source.remote.auth

import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            throw when (response.code) {
                401 -> UnauthorizedException()
                in 500..599 -> ServerErrorException(response.code)
                else -> ApiException(response.code, response.message)
            }
        }

        return response
    }

    private fun ApiException(i: Int, string: String): Nothing {
        throw Exception("ApiException Code: $i, Message: $string")
    }

    private fun ServerErrorException(i: Int): Nothing {
        throw Exception("ServerErrorException")
    }

    private fun UnauthorizedException(): Nothing = throw Exception("UnauthorizedException")

}
