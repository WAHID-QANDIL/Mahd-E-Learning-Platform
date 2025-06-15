package org.mahd_e_learning_platform.data.source.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCourseResponseDto(
    val `data`: Course? = null,
    val message: String? = null,
    val status: Int? = null,
    val success: Boolean? = null
)