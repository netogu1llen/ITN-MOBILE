package com.app.soffyapp.di

import com.app.soffyapp.data.remote.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides network-related dependencies.
 *
 * This module is installed in the [SingletonComponent] to ensure that the provided dependencies
 * have application-wide scope. It provides:
 * - A [Retrofit] instance configured with a base URL and Gson converter
 * - An [AuthApiService] implementation created from the Retrofit instance
 *
 * The base URL should be replaced with the actual API endpoint before use in production.
 * Consider moving sensitive configuration like base URLs to a secure configuration source.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a singleton Retrofit instance configured with the base API URL and Gson converter.
     *
     * @return Configured [Retrofit] instance with Gson converter factory
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.tudominio.com/") // TODO: Replace with actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides an [AuthApiService] implementation using the given Retrofit instance.
     *
     * @param retrofit The Retrofit instance used to create the service
     * @return Implementation of [AuthApiService]
     */
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }
}