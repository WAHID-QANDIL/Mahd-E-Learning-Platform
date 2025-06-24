package org.mahd_e_learning_platform.data.source.remote.model

import kotlinx.serialization.Serializable
import org.mahd_e_learning_platform.domain.model.Course

@Serializable
data class Course(
    val id: String,
    val approved: Boolean? = null,
    val createdAt: String? = null,
    val description: String? = null,
    val educator: String? = null,
    val educatorId: String? = null,
    val enrollmentCount: Int? = null,
    val imageUrl: String? = null,
    val level: String? = null,
    val price: Float? = null,
    val rating: List<Float>? = null,
    val sections: List<String>? = null,
    val tags: List<String?>? = null,
    val title: String? = null,
    val updatedAt: String? = null,
) {
    fun toDomain(): Course {
        return Course(
            courseId = id,
            courseImageUrl = imageUrl ?: "",
            courseTitle = title ?: "",
            courseDescription = description ?: "",
            enrollments = enrollmentCount ?: 0,
            rate = rating  ?: emptyList(),
            cost = price ?: 0F,
            progress = 0f,
            educator = educator ?: "",
            level = level ?: "",
            sections = sections ?: emptyList(),
            category = ""
        )

    }
}