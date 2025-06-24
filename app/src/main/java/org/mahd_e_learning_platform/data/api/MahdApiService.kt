package org.mahd_e_learning_platform.data.api

import org.mahd_e_learning_platform.BuildConfig
import org.mahd_e_learning_platform.data.api.model.LoginResponse
import org.mahd_e_learning_platform.data.api.model.UserProfile
import org.mahd_e_learning_platform.data.source.remote.model.Course
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


interface MahdApiService {
    @POST("${BuildConfig.BASEURL}${BuildConfig.UMS_PORT_NUMBER}api/v1/ums/auth/login")
    suspend fun login(@Body loginRequest: Map<String, String>): Response<LoginResponse>

    @POST("${BuildConfig.BASEURL}${BuildConfig.UMS_PORT_NUMBER}api/v1/ums/auth/register")
    suspend fun register(@Body registerRequest: Map<String, String>)

    @GET("${BuildConfig.BASEURL}${BuildConfig.UMS_PORT_NUMBER}api/v1/ums/user/profile")
    suspend fun getUserProfile(): Response<UserProfile>

    @PUT("${BuildConfig.BASEURL}${BuildConfig.UMS_PORT_NUMBER}api/v1/ums/user/profile")
    suspend fun updateUserProfile(@Body newUserInfo: Map<String, String>)

    @GET("${BuildConfig.BASEURL}${BuildConfig.RECOMMENDATION_PORT_NUMBER}recommendation/top5")
    suspend fun getRecommendedCourses(): Response<List<Course>>

}