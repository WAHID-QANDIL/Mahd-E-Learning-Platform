package org.mahd_e_learning_platform.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mahd_e_learning_platform.BuildConfig
import org.mahd_e_learning_platform.data.api.MahdApiService
import org.mahd_e_learning_platform.data.source.remote.auth.AuthInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)      // Applies auth header
//            .addInterceptor(ErrorInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
//            .addNetworkInterceptor { chain->
//                val originalRequest = chain.request()
//                val newRequest = originalRequest
//                    .newBuilder()
//                    .header("Authorization", "Bearer $token")
//                    .build()
//                chain.proceed(newRequest)
//            }
            // Logs everything
            .connectTimeout(30, TimeUnit.SECONDS) // Network timeouts
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitMahdApiService(
        retrofit: Retrofit,
    ) = retrofit.create(MahdApiService::class.java)


}