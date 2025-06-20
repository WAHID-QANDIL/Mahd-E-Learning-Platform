package org.mahd_e_learning_platform.domain.usecase

import org.mahd_e_learning_platform.domain.repository.Repository
import javax.inject.Inject

class Login @Inject constructor(
   private val repository: Repository,
){
    suspend operator fun invoke(email:String, password: String){
        repository.login(
            email = email,
            password = password
        )
    }
}