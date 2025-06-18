package org.mahd_e_learning_platform.data.api

import org.mahd_e_learning_platform.data.source.remote.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface MahdApiService {

    @POST("api/v1/ums/auth/login")
    suspend fun login(@Body loginRequest: Map<String, String>): retrofit2.Response<LoginResponse>

    @POST("api/v1/ums/auth/register")
    suspend fun register(@Body registerRequest: Map<String, String>)
}