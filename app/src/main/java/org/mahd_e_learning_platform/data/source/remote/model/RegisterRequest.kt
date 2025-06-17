package org.mahd_e_learning_platform.data.source.remote.model

data class RegisterRequest(
    val firstName: String,
    val lastName:  String,
    val email:     String,
    val password:  String,
    val role:      String,
)
