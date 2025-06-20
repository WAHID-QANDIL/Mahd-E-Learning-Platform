package org.mahd_e_learning_platform.data.source.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "current_student"
)
data class StudentEntity(
    val firstName: String,
    val lastname: String,
    @PrimaryKey val email: String,
    val password: String,
    val role: String = "student",
)