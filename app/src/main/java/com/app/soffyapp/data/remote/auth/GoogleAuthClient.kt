package com.app.soffyapp.data.remote.auth

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import com.app.soffyapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.google.android.material.R.integer
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Cliente para manejar la autenticación con Google
 * Con mejoras para solucionar problemas de DEVELOPER_ERROR
 */
@Singleton
class GoogleAuthClient @Inject constructor(
    private val context: Context,
) {
    private val TAG = "GoogleAuthClient"

    // Obtiene el client ID de los recursos
    private val clientId = context.getString(R.string.google_cloud_client_id)

    // Variante con prefijo oauth2: (puede resolver algunos problemas de DEVELOPER_ERROR)
    private val clientIdWithPrefix = "oauth2:$clientId"

    // Métodos alternativos de configuración
    private val gsoAlternative1 = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestProfile()
        .build()

    private val gsoAlternative2 = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(clientIdWithPrefix) // Usar variante con prefijo
        .requestEmail()
        .requestProfile()
        .build()

    private val gsoAlternative3 = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(clientId)
        .requestEmail()
        .requestProfile()
        .requestServerAuthCode(clientId) // Agregar solicitud de código de autorización
        .build()

    // Configuración principal (original)
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(clientId)
        .requestEmail()
        .build()

    // Crea el cliente de Google Sign-In (usamos la configuración principal por defecto)
    private var googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    // Variable para rastrear qué configuración estamos usando
    private var currentConfigIndex = 0
    private val CONFIGS = 4 // Ahora tenemos 4 configuraciones

    init {
        // Verificar la configuración cuando se inicializa el cliente
        verifyConfiguration()

        // Intenta revocar accesos previos al inicializar para evitar problemas de caché
        try {
            googleSignInClient.revokeAccess()
        } catch (e: Exception) {
            Log.d(TAG, "No se pudo revocar accesos previos: ${e.message}")
        }
    }

    /**
     * Verifica la configuración del cliente Google Sign-In
     */
    private fun verifyConfiguration() {
        try {
            Log.d(TAG, "====== VERIFICACIÓN DE CONFIGURACIÓN GOOGLE SIGN-IN ======")
            Log.d(TAG, "Client ID configurado: $clientId")
            Log.d(TAG, "Nombre del paquete: ${context.packageName}")

            // Obtener y mostrar las huellas SHA-1 y SHA-256 de la aplicación
            val sha1 = getAppSignatureSHA1()
            val sha256 = getAppSignatureSHA256()
            Log.d(TAG, "Huella SHA-1 de la aplicación: $sha1")
            Log.d(TAG, "Huella SHA-256 de la aplicación: $sha256")

            // Verificar disponibilidad de Google Play Services
            val googleApiAvailability = GoogleApiAvailability.getInstance()
            val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)
            if (resultCode != com.google.android.gms.common.ConnectionResult.SUCCESS) {
                Log.e(TAG, "Google Play Services no disponible. Código: $resultCode")
                val errorString = googleApiAvailability.getErrorString(resultCode)
                Log.e(TAG, "Error de Google Play Services: $errorString")
            } else {
                Log.d(TAG, "Google Play Services está disponible correctamente")
            }

            // Verificar si tenemos una cuenta activa ya
            val account = GoogleSignIn.getLastSignedInAccount(context)
            if (account != null) {
                Log.d(TAG, "Cuenta activa encontrada: ${account.email}")
                Log.d(TAG, "Tiene token ID: ${account.idToken != null}")
            } else {
                Log.d(TAG, "No hay cuentas activas de Google Sign-In")
            }

            Log.d(TAG, "====== FIN DE VERIFICACIÓN ======")
        } catch (e: Exception) {
            Log.e(TAG, "Error durante la verificación: ${e.message}")
            e.printStackTrace()
        }
    }

    /**
     * Obtiene la huella SHA-1 de la aplicación
     */
    private fun getAppSignatureSHA1(): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            )
            val signatures = packageInfo.signatures
            val signatureBytes = signatures?.get(0)?.toByteArray()
            if (signatureBytes != null && signatureBytes.isNotEmpty()) {
                val md = MessageDigest.getInstance("SHA-1")
                val digest = md.digest(signatureBytes)
                return bytesToHexString(digest)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener la huella SHA-1: ${e.message}")
        }
        return "No se pudo obtener"
    }

    /**
     * Obtiene la huella SHA-256 de la aplicación (algunas APIs de Google ahora prefieren SHA-256)
     */
    private fun getAppSignatureSHA256(): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            )
            val signatures = packageInfo.signatures
            val signatureBytes = signatures?.get(0)?.toByteArray()
            if (signatureBytes != null && signatureBytes.isNotEmpty()) {
                val md = MessageDigest.getInstance("SHA-256")
                val digest = md.digest(signatureBytes)
                return bytesToHexString(digest)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener la huella SHA-256: ${e.message}")
        }
        return "No se pudo obtener"
    }

    /**
     * Convierte bytes a string hexadecimal con formato
     */
    private fun bytesToHexString(bytes: ByteArray): String {
        val hexString = StringBuilder()
        for (i in bytes.indices) {
            if (i > 0) hexString.append(":")
            val hex = Integer.toHexString(0xff and bytes[i].toInt())
            if (hex.length == 1) hexString.append("0")
            hexString.append(hex)
        }
        return hexString.toString()
    }

    /**
     * Cambia a la siguiente configuración de autenticación
     * @return booleano que indica si hay más configuraciones disponibles
     */
    fun switchToNextConfig(): Boolean {
        currentConfigIndex = (currentConfigIndex + 1) % CONFIGS
        Log.d(TAG, "Cambiando a configuración alternativa #$currentConfigIndex")

        googleSignInClient = when (currentConfigIndex) {
            0 -> GoogleSignIn.getClient(context, gso)
            1 -> GoogleSignIn.getClient(context, gsoAlternative1)
            2 -> GoogleSignIn.getClient(context, gsoAlternative2)
            3 -> GoogleSignIn.getClient(context, gsoAlternative3)
            else -> GoogleSignIn.getClient(context, gso)
        }

        // Intentar limpiar estado de Google Play Services para evitar caché
        try {
            googleSignInClient.signOut().addOnCompleteListener {
                // No necesitamos esperar, solo queremos limpiar el estado
                Log.d(TAG, "Sesión limpiada antes de cambiar configuración")
            }
        } catch (e: Exception) {
            Log.d(TAG, "No se pudo limpiar sesión: ${e.message}")
        }

        return currentConfigIndex != 0 // Devuelve false si hemos dado la vuelta completa
    }

    /**
     * Obtiene el intent para iniciar el flujo de inicio de sesión con Google
     */
    fun getSignInIntent(): Intent {
        // Registra el proceso para debugging
        Log.d(TAG, "Iniciando proceso de Sign-In con Google. Usando configuración #$currentConfigIndex")
        return googleSignInClient.signInIntent
    }

    /**
     * Procesa el resultado de la actividad de inicio de sesión para obtener el token ID
     * @param intent Intent devuelto de la actividad de inicio de sesión
     * @return Result con el token ID o error
     */
    suspend fun handleSignInResult(intent: Intent?): Result<String> {
        if (intent == null) {
            Log.e(TAG, "Intent es nulo, no se puede procesar el resultado")
            return Result.failure(Exception("No se recibieron datos de inicio de sesión"))
        }

        return try {
            // Obtener tarea de la cuenta con el intent
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)

            try {
                // Extraer la cuenta del resultado
                val account = task.await()

                // Si estamos usando una configuración que no pide token, devolver el email como identificador
                if (currentConfigIndex == 1) {
                    val email = account.email
                    if (email.isNullOrEmpty()) {
                        Log.e(TAG, "Email nulo o vacío")
                        Result.failure(Exception("No se pudo obtener el email de Google"))
                    } else {
                        Log.d(TAG, "Email obtenido correctamente: $email")
                        return Result.success(email)
                    }
                } else {
                    // Procesar normalmente con token
                    val idToken = account.idToken
                    if (idToken.isNullOrEmpty()) {
                        // Intentar con serverAuthCode para configuración 3
                        if (currentConfigIndex == 3 && account.serverAuthCode != null) {
                            val authCode = account.serverAuthCode!!
                            Log.d(TAG, "Utilizando serverAuthCode en lugar de token")
                            return Result.success("authcode:$authCode")
                        }

                        Log.e(TAG, "Token ID nulo o vacío")
                        Result.failure(Exception("No se pudo obtener el token de Google"))
                    } else {
                        // Mostrar parte del token para debugging (no el token completo por seguridad)
                        val tokenPreview = idToken.take(15) + "..." + idToken.takeLast(10)
                        Log.d(TAG, "Token ID obtenido correctamente: $tokenPreview")
                        Result.success(idToken)
                    }
                }
            } catch (e: ApiException) {
                // Manejo de errores específicos de la API de Google
                val statusCode = e.statusCode
                val statusMessage = GoogleSignInStatusToString(statusCode)
                val errorMessage = when (statusCode) {
                    // Códigos de error comunes de Google Sign-In
                    10 -> "Error de configuración (DEVELOPER_ERROR). Verifica que el ID de cliente y la huella digital SHA-1 estén correctamente configurados en Google Cloud Console."
                    12500 -> "Error de Google Play Services, actualiza la aplicación"
                    12501 -> "Inicio de sesión cancelado por el usuario"
                    12502 -> "Error de conexión, verifica tu internet"
                    16 -> "Error interno de Google Sign-In. Intenta reiniciar la aplicación."
                    8 -> "Error de alcance (SCOPE) en la solicitud"
                    else -> "Error en la autenticación: Código $statusCode"
                }
                Log.e(TAG, "ApiException: $errorMessage (Código: $statusCode, Mensaje: $statusMessage)")

                // Para error código 10, mostrar información adicional de diagnóstico
                if (statusCode == 10) {
                    Log.e(TAG, "DETALLE DE ERROR DEVELOPER_ERROR (10): Verifica que:")
                    Log.e(TAG, "1. El ID de cliente ($clientId) coincida exactamente con el de Google Cloud Console")
                    Log.e(TAG, "2. El nombre del paquete (${context.packageName}) coincida con el configurado")
                    Log.e(TAG, "3. La huella SHA-1 (${getAppSignatureSHA1()}) esté registrada correctamente en Google Cloud Console")
                    Log.e(TAG, "4. La API de Google Sign-In esté habilitada en Google Cloud Console")
                    Log.e(TAG, "5. La pantalla de consentimiento OAuth esté configurada correctamente")
                    Log.e(TAG, "Configuración actual: #$currentConfigIndex")

                    // Sugiere cambiar a otra configuración
                    Log.e(TAG, "Prueba con: switchToNextConfig() para usar otra configuración")
                }

                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            // Manejo de otros errores no específicos
            Log.e(TAG, "Error inesperado: ${e.message}")
            e.printStackTrace()
            Result.failure(Exception("Error inesperado: ${e.message}"))
        }
    }

    /**
     * Cierra la sesión actual del usuario con Google
     */
    suspend fun signOut() {
        try {
            googleSignInClient.signOut().await()
            Log.d(TAG, "Sesión cerrada correctamente")
        } catch (e: Exception) {
            Log.e(TAG, "Error al cerrar sesión: ${e.message}")
            throw e
        }
    }

    /**
     * Convierte códigos de estado de Google Sign-In a strings descriptivos
     */
    private fun GoogleSignInStatusToString(statusCode: Int): String {
        return when (statusCode) {
            0 -> "SUCCESS"
            2 -> "SERVICE_VERSION_UPDATE_REQUIRED"
            3 -> "SERVICE_DISABLED"
            4 -> "SIGN_IN_REQUIRED"
            5 -> "INVALID_ACCOUNT"
            6 -> "RESOLUTION_REQUIRED"
            7 -> "NETWORK_ERROR"
            8 -> "INTERNAL_ERROR"
            10 -> "DEVELOPER_ERROR"
            13 -> "ERROR"
            14 -> "INTERRUPTED"
            15 -> "TIMEOUT"
            16 -> "CANCELED"
            17 -> "API_NOT_CONNECTED"
            18 -> "DEAD_CLIENT"
            21 -> "RECONNECTION_TIMED_OUT"
            22 -> "RECONNECTION_TIMED_OUT_DURING_UPDATE"
            12500 -> "PLAY_SERVICES_NOT_AVAILABLE"
            12501 -> "SIGN_IN_CANCELLED"
            12502 -> "NETWORK_ERROR"
            else -> "UNKNOWN_STATUS_CODE"
        }
    }
}