package org.mahd_e_learning_platform.data.source.remote.auth

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val secureTokenStore: SecureTokenStore) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        var token: String? = secureTokenStore.getTokenSynchronously()
        val requestWithToken = token?.let {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
        } ?:originalRequest

        return chain.proceed(requestWithToken)
    }


}