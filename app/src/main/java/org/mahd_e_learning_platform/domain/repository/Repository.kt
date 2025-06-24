package org.mahd_e_learning_platform.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mahd_e_learning_platform.data.api.model.RegisterRequest
import org.mahd_e_learning_platform.data.api.model.UserProfile
import org.mahd_e_learning_platform.domain.model.Course

interface Repository {
    suspend fun login(email: String, password: String)
    suspend fun register(registerRequest: RegisterRequest)
    suspend fun getUserProfile(): UserProfile
    suspend fun updateUserProfile(newUserInfo: Map<String, String>)
    suspend fun getRecommendedCourses(): Flow<List<Course>>
}