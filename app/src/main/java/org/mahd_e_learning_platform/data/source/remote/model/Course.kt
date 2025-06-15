package org.mahd_e_learning_platform.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: String,
    val approved: Boolean? = null,
    val createdAt: String? = null,
    val description: String? = null,
    val educator: String? = null,
    val educatorId: String? = null,
    val enrollmentCount: Int? = null,
    val level: String? = null,
    val price: Int? = null,
    val rating: Double? = null,
    val sections: List<String?>? = null,
    val tags: List<String?>? = null,
    val title: String? = null,
    val updatedAt: String? = null
)