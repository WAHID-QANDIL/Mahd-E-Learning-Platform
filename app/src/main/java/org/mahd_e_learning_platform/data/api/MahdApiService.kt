package org.mahd_e_learning_platform.data.api

import org.mahd_e_learning_platform.BuildConfig
import org.mahd_e_learning_platform.data.source.remote.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface MahdApiService {

    @POST("${BuildConfig.BASEURL}${BuildConfig.UMS_PORT_NUMBER}api/v1/ums/auth/login")
    suspend fun login(@Body loginRequest: Map<String, String>): retrofit2.Response<LoginResponse>

    @POST("${BuildConfig.BASEURL}${BuildConfig.UMS_PORT_NUMBER}api/v1/ums/auth/register")
    suspend fun register(@Body registerRequest: Map<String, String>)
}