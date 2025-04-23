package com.app.soffyapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
/**
 * Delegado para crear/obtener el DataStore de preferencias
 *
 * Define un DataStore con nombre "auth" para almacenar datos de autenticación.
 * Se accede a través de la propiedad de extensión `dataStore` en cualquier Context.
 */
val Context.dataStore by preferencesDataStore(name = "auth")

/**
 * Objeto singleton para manejar operaciones con el token JWT
 *
 * Proporciona operaciones seguras para:
 * - Guardar el token después del login
 * - Obtener el token actual
 * - Eliminar el token durante logout
 *
 * Usa Preferences DataStore (recomendado por Google para datos simples)
 */
object TokenDataStore {

    // Clave para almacenar el token JWT en las preferencias
    private val JWT_KEY = stringPreferencesKey("jwt_token")

    /**
     * Guarda el token JWT en el DataStore
     * @param context Contexto de la aplicación
     * @param token Token JWT a almacenar
     */
    suspend fun guardarToken(context: Context, token: String) {
        context.dataStore.edit { prefs -> // Operación suspendida de escritura
            prefs[JWT_KEY] = token // Asigna el valor al DataStore
        }
    }

    /**
     * Obtiene el token almacenado como un Flow
     * @param context Contexto de la aplicación
     * @return Flow que emite el token cuando está disponible (o null)
     */
    fun obtenerToken(context: Context): Flow<String?> {
        return context.dataStore.data // Flow del DataStore
            .map { prefs -> // Transforma las preferencias al token
                prefs[JWT_KEY]
            }
    }

    /**
     * Elimina el token almacenado (para logout)
     * @param context Contexto de la aplicación
     */
    suspend fun borrarToken(context: Context) {
        context.dataStore.edit { prefs -> // Operación suspendida de escritura
            prefs.remove(JWT_KEY) // Elimina la clave del DataStore
        }
    }
}