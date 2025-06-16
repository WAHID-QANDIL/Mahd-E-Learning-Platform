package org.mahd_e_learning_platform.data.source.remote.auth

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val secureTokenStore: SecureTokenStore) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val scope = CoroutineScope(context = Dispatchers.IO)
        var token: String? = null
        scope.launch {
             secureTokenStore.accessTokenFlow.collect{
                token = it
            }
        }
        val requestWithToken = token?.let {
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $it")
                .build()
        } ?:originalRequest

        return chain.proceed(requestWithToken)
    }


}