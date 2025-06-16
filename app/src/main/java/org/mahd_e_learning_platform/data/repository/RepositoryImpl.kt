package org.mahd_e_learning_platform.data.repository

import android.util.Log
import jakarta.inject.Inject
import org.mahd_e_learning_platform.data.api.MahdApiService
import org.mahd_e_learning_platform.data.source.remote.auth.SecureTokenStore
import org.mahd_e_learning_platform.domain.repository.Repository

class RepositoryImpl @Inject constructor(
    private val secureTokenStore: SecureTokenStore,
    private val mahdApiService: MahdApiService
) : Repository {
    override suspend fun login(email: String, password: String) {
        val requestBody = mapOf<String, String>("email" to email, "password" to password)

       val loginResponse =  mahdApiService.login(requestBody)
        loginResponse.accessToken?.let {
                secureTokenStore.saveAccessToken(loginResponse.accessToken)
                Log.d("accessToken", "accessToken: ${loginResponse.accessToken}")
        }
    }

    override fun register() {
        TODO("Not yet implemented")
    }
}