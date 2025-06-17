package org.mahd_e_learning_platform.domain.repository

import org.mahd_e_learning_platform.data.source.remote.model.RegisterRequest

interface Repository {
    suspend fun login(email: String, password: String)
    suspend fun register(registerRequest: RegisterRequest)
}