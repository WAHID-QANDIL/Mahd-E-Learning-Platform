package org.mahd_e_learning_platform.domain.repository

interface Repository {
    suspend fun login(email: String, password: String)
    fun register()
}