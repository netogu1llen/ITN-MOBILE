package com.app.soffyapp.di

import com.app.soffyapp.data.remote.AuthApiService
import com.app.soffyapp.data.remote.api.CentroEducativoApi
import com.app.soffyapp.data.remote.api.ExpedienteApi
import com.app.soffyapp.data.remote.api.PacientesApi
import com.app.soffyapp.data.repository.CentroEducativoRepositoryImpl
import com.app.soffyapp.data.repository.ExpedienteRepositoryImpl
import com.app.soffyapp.data.repository.PacientesRepositoryImpl
import com.app.soffyapp.domain.repository.CentroEducativoRepository
import com.app.soffyapp.domain.repository.ExpedienteRepository
import com.app.soffyapp.domain.repository.PacientesRepository
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
        authInterceptor: AuthInterceptor,
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    /**
     * Provides a singleton Retrofit instance configured with the base API URL and Gson converter.
     *
     * @param okHttpClient The OkHttpClient instance
     * @return Configured [Retrofit] instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Provides an [AuthApiService] implementation using the given Retrofit instance.
     *
     * @param retrofit The Retrofit instance used to create the service
     * @return Implementation of [AuthApiService]
     */
    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService = retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun providePacientesApi(retrofit: Retrofit): PacientesApi = retrofit.create(PacientesApi::class.java)

    @Provides
    @Singleton
    fun providePacientesRepository(api: PacientesApi): PacientesRepository = PacientesRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideExpedienteApi(retrofit: Retrofit): ExpedienteApi = retrofit.create(ExpedienteApi::class.java)

    @Provides
    @Singleton
    fun provideExpedienteRepository(api: ExpedienteApi): ExpedienteRepository = ExpedienteRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCentroEducativoApi(retrofit: Retrofit): CentroEducativoApi = retrofit.create(CentroEducativoApi::class.java)

    @Provides
    @Singleton
    fun provideCentroEducativoRepository(api: CentroEducativoApi): CentroEducativoRepository = CentroEducativoRepositoryImpl(api)
}
