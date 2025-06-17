package org.mahd_e_learning_platform.domain.usecase

import org.mahd_e_learning_platform.data.source.remote.model.RegisterRequest
import org.mahd_e_learning_platform.domain.repository.Repository
import javax.inject.Inject

class Register @Inject constructor(
    private val repository: Repository,
) {

    suspend operator fun invoke(registerRequest: RegisterRequest) {
        repository.register(registerRequest = registerRequest)
    }

}