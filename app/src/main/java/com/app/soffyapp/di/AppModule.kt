package com.app.soffyapp.di

import com.app.soffyapp.data.remote.api.PacientesApi
import com.app.soffyapp.data.repository.PacientesRepositoryImpl
import com.app.soffyapp.domain.repository.PacientesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di/AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("http://10.0.2.2:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePacientesApi(retrofit: Retrofit): PacientesApi = retrofit.create(PacientesApi::class.java)

    @Provides
    @Singleton
    fun providePacientesRepository(api: PacientesApi): PacientesRepository = PacientesRepositoryImpl(api)
}
