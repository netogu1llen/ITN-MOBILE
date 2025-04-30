package com.app.soffyapp.di

import com.app.soffyapp.data.remote.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides network-related dependencies.
 *
 * This module is installed in the [SingletonComponent] to ensure that the provided dependencies
 * have application-wide scope. It provides:
 * - An OkHttpClient with logging and authentication capabilities
 * - A [Retrofit] instance configured with a base URL and Gson converter
 * - An [AuthApiService] implementation created from the Retrofit instance
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides a singleton HttpLoggingInterceptor instance configured for body logging.
     *
     * @return Configured [HttpLoggingInterceptor] instance
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    /**
     * Provides a singleton OkHttpClient with the logging interceptor, authentication, and timeouts.
     *
     * @param loggingInterceptor The HTTP logging interceptor
     * @param authInterceptor The authentication interceptor
     * @return Configured [OkHttpClient] instance
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provides a singleton Retrofit instance configured with the base API URL and Gson converter.
     *
     * @param okHttpClient The OkHttpClient instance
     * @return Configured [Retrofit] instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/") // Replace with your actual base URL
            .client(okHttpClient)
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