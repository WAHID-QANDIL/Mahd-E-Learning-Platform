package org.mahd_e_learning_platform.data.api.model

import kotlinx.serialization.Serializable
import org.mahd_e_learning_platform.data.source.local.db.model.StudentEntity

@Serializable
data class UserProfile(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: String,
)
{
    fun toDatabaseEntity():StudentEntity{
        return StudentEntity(
            firstName = firstName,
            lastname = lastName,
            email = email,
            password = "",
            role = role
        )
    }
}
