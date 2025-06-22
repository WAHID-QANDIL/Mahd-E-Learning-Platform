package org.mahd_e_learning_platform.data.repository

import android.util.Log
import jakarta.inject.Inject
import org.mahd_e_learning_platform.data.api.MahdApiService
import org.mahd_e_learning_platform.data.api.model.RegisterRequest
import org.mahd_e_learning_platform.data.api.model.UserProfile
import org.mahd_e_learning_platform.data.exception.ExceptionHandler
import org.mahd_e_learning_platform.data.source.local.datastore.SecureTokenStore
import org.mahd_e_learning_platform.data.source.local.db.MahdDatabase
import org.mahd_e_learning_platform.domain.repository.Repository

class RepositoryImpl @Inject constructor(
    private val secureTokenStore: SecureTokenStore,
    private val mahdApiService: MahdApiService,
    database: MahdDatabase,
) : Repository {
    private val studentDao = database.getStudentDao()
    override suspend fun login(email: String, password: String) {
        try {
            val requestBody = mapOf<String, String>("email" to email, "password" to password)
            val loginResponse = mahdApiService.login(requestBody)
            val token = loginResponse.body()?.accessToken ?: throw ExceptionHandler.ServerErrorException(
                    "Failed to retrieve access token. Response body: ${loginResponse.body()?.toString()}" );
            secureTokenStore.saveAccessToken(token)
            Log.d(
                "accessToken",
                "accessToken: ${loginResponse.body()?.accessToken.toString()}"
            )
        } catch (
            e: Exception,
        ) {
            throw e
        }


    }

    override suspend fun register(registerRequest: RegisterRequest) {
        val student = mapOf(
            "firstName" to registerRequest.firstName,
            "lastName" to registerRequest.lastName,
            "email" to registerRequest.email,
            "password" to registerRequest.password,
            "role" to registerRequest.role,
        )
        mahdApiService.register(
            registerRequest = student
        )

    }

    override suspend fun getUserProfile(): UserProfile {
       val response =  mahdApiService.getUserProfile().body()?: throw Exception("Cant get user profile linked with this email.")
        return UserProfile(
            id = response.id,
            firstName = response.firstName,
            lastName = response.lastName,
            email = response.email,
            role = response.role
        )
    }


    override suspend fun updateUserProfile(newUserInfo: Map<String, String>) =
        mahdApiService.updateUserProfile(newUserInfo = newUserInfo)
}