package com.app.soffyapp.di

import android.content.Context
import com.app.soffyapp.R
import com.app.soffyapp.data.remote.auth.GoogleAuthClient
import com.app.soffyapp.data.repository.AuthRepositoryImpl
import com.app.soffyapp.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides authentication-related dependencies.
 *
 * This module is installed in the [SingletonComponent] to ensure that authentication dependencies
 * have application-wide scope. Currently provides:
 * - [GoogleAuthClient] configured with the Google OAuth client ID
 * - [AuthRepository] for authentication operations
 */
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    /**
     * Provides a singleton [GoogleAuthClient] instance configured with the Google OAuth client ID.
     *
     * @param context Application context required for Google Auth operations
     * @return Configured [GoogleAuthClient] instance
     */
    @Provides
    @Singleton
    fun provideGoogleAuthClient(
        @ApplicationContext context: Context
    ): GoogleAuthClient {
        // Prefer getting client ID from resources for easier environment-specific configuration
        val clientId = context.getString(R.string.google_cloud_client_id)

        // Alternative: Get from BuildConfig if you prefer compile-time configuration
        // val clientId = BuildConfig.GOOGLE_CLIENT_ID

        return GoogleAuthClient(context, clientId)
    }

    /**
     * Provides a singleton implementation of [AuthRepository]
     *
     * @param googleAuthClient The Google authentication client
     * @return Implementation of [AuthRepository]
     */
    @Provides
    @Singleton
    fun provideAuthRepository(
        googleAuthClient: GoogleAuthClient
        // Add any other dependencies your implementation needs
    ): AuthRepository {
        return AuthRepositoryImpl(googleAuthClient)
    }
}