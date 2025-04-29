package com.app.soffyapp.di

import android.content.Context
import com.app.soffyapp.R
import com.app.soffyapp.data.remote.AuthApiService
import com.app.soffyapp.data.remote.auth.GoogleAuthClient
import com.app.soffyapp.data.repository.AuthRepositoryImpl
import com.app.soffyapp.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Módulo Dagger Hilt que proporciona dependencias relacionadas con autenticación.
 */
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    /**
     * Proporciona una instancia singleton de [GoogleAuthClient] configurada con el ID de cliente OAuth de Google.
     */
    @Provides
    @Singleton
    fun provideGoogleAuthClient(
        @ApplicationContext context: Context
    ): GoogleAuthClient {
        val clientId = context.getString(R.string.google_cloud_client_id)
        return GoogleAuthClient(context, clientId)
    }

    /**
     * Proporciona una implementación singleton de [AuthRepository]
     */
    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService: AuthApiService
    ): AuthRepository {
        return AuthRepositoryImpl(authApiService)
    }
}