package org.mahd_e_learning_platform.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.mahd_e_learning_platform.domain.model.Course
import org.mahd_e_learning_platform.domain.repository.Repository
import javax.inject.Inject

class GetRecommendedCourses @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): Flow<List<Course>>{
      return  repository.getRecommendedCourses()
    }

}