package com.app.soffyapp.data.localy

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

// Extension para DataStore
private val Context.dataStore by preferencesDataStore(name = "user_preferences")

/**
 * Almacena y gestiona el token JWT usando DataStore
 */
@Singleton
class TokenDataStore
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) {
        private val tokenKey = stringPreferencesKey("auth_token")

        /**
         * Guarda el token JWT en DataStore
         * @param token Token JWT a guardar
         */
        suspend fun saveToken(token: String) {
            context.dataStore.edit { preferences ->
                preferences[tokenKey] = token
            }
        }

        /**
         * Obtiene el token JWT guardado
         * @return Flow con el token o null si no existe
         */
        fun getToken(): Flow<String?> =
            context.dataStore.data.map { preferences ->
                preferences[tokenKey]
            }

        /**
         * Elimina el token guardado (logout)
         */
        suspend fun clearToken() {
            context.dataStore.edit { preferences ->
                preferences.remove(tokenKey)
            }
        }
    }
