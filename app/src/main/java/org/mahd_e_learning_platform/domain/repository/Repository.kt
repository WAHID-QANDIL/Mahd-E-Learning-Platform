package org.mahd_e_learning_platform.domain.repository

import org.mahd_e_learning_platform.data.api.model.RegisterRequest
import org.mahd_e_learning_platform.data.api.model.UserProfile

interface Repository {
    suspend fun login(email: String, password: String)
    suspend fun register(registerRequest: RegisterRequest)
    suspend fun getUserProfile(): UserProfile
    suspend fun updateUserProfile(newUserInfo: Map<String, String>)
}