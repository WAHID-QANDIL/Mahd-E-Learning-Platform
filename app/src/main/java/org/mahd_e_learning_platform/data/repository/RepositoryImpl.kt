package org.mahd_e_learning_platform.data.repository

import android.util.Log
import jakarta.inject.Inject
import org.mahd_e_learning_platform.data.api.MahdApiService
import org.mahd_e_learning_platform.data.source.remote.auth.SecureTokenStore
import org.mahd_e_learning_platform.data.source.remote.model.RegisterRequest
import org.mahd_e_learning_platform.domain.repository.Repository

class RepositoryImpl @Inject constructor(
    private val secureTokenStore: SecureTokenStore,
    private val mahdApiService: MahdApiService
) : Repository {
    override suspend fun login(email: String, password: String) {
        val requestBody = mapOf<String, String>("email" to email, "password" to password)
        try {

            val loginResponse =  mahdApiService.login(requestBody)
            if (loginResponse.isSuccessful){
                loginResponse.body()?.accessToken?.let {
                    secureTokenStore.saveAccessToken(loginResponse.body()?.accessToken.toString())
                    Log.d("accessToken", "accessToken: ${loginResponse.body()?.accessToken.toString()}")
                }
            }
        }catch (e: Exception){
            throw e
        }

    }

    override suspend fun register(registerRequest: RegisterRequest) {
        mahdApiService.register(
            registerRequest = mapOf(
                "firstName" to registerRequest.firstName,
                "lastName" to registerRequest.lastName,
                "email" to registerRequest.email,
                "password" to registerRequest.password,
                "role" to registerRequest.role,
            )
        )
    }
}