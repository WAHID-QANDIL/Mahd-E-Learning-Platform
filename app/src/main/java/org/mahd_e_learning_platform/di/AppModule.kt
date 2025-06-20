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
import org.mahd_e_learning_platform.data.source.local.datastore.SecureTokenStore
import org.mahd_e_learning_platform.data.source.local.db.MahdDatabase
import org.mahd_e_learning_platform.domain.repository.Repository
import org.mahd_e_learning_platform.domain.usecase.AuthUseCases
import org.mahd_e_learning_platform.domain.usecase.GetUserProfile
import org.mahd_e_learning_platform.domain.usecase.Login
import org.mahd_e_learning_platform.domain.usecase.ProfileUseCases
import org.mahd_e_learning_platform.domain.usecase.Register
import org.mahd_e_learning_platform.domain.usecase.UpdateProfile
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
        database: MahdDatabase,
    ): Repository =
        RepositoryImpl(
            secureTokenStore = secureTokenStore,
            mahdApiService = mahdApiService,
            database = database
        )

    @Provides
    @Singleton
    fun providesAuthUseCases(
        repository: Repository,
    ): AuthUseCases = AuthUseCases(
        loginUseCase = Login(repository = repository),
        registerUseCase = Register(repository = repository)
    )
    @Provides
    @Singleton
    fun providesProfileUseCases(
        repository: Repository,
    ): ProfileUseCases = ProfileUseCases(
        getUserProfile = GetUserProfile(repository = repository),
        updateProfile = UpdateProfile(repository = repository)
    )


}