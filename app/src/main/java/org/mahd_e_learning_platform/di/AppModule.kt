package org.mahd_e_learning_platform.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mahd_e_learning_platform.data.api.MahdApiService
import org.mahd_e_learning_platform.data.repository.RepositoryImpl
import org.mahd_e_learning_platform.data.source.remote.auth.AuthInterceptor
import org.mahd_e_learning_platform.data.source.remote.auth.SecureTokenStore
import org.mahd_e_learning_platform.domain.repository.Repository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesSecureTokenStore(@ApplicationContext ctx: Context): SecureTokenStore {
        return SecureTokenStore(context = ctx)
    }

    @Provides
    @Singleton
    fun providesAuthInterceptor(secureTokenStore: SecureTokenStore): AuthInterceptor {
        return AuthInterceptor(
            secureTokenStore = secureTokenStore
        )
    }

    @Provides
    @Singleton
    fun providesRepository(
        secureTokenStore: SecureTokenStore,
        mahdApiService: MahdApiService,
    ): Repository =
        RepositoryImpl(secureTokenStore = secureTokenStore, mahdApiService = mahdApiService)


}