package org.mahd_e_learning_platform.domain.usecase

import org.mahd_e_learning_platform.domain.repository.Repository
import javax.inject.Inject

class UpdateProfile @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(firstname: String, lastName: String) {
        repository.updateUserProfile(
            mapOf(
                "firstName" to firstname,
                "lastName" to lastName
            )
        )

    }
}