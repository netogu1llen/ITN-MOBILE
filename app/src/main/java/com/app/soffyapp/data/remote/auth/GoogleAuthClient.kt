package com.app.soffyapp.data.remote.auth

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Cliente para manejar la autenticación con Google
 * Proporciona métodos para iniciar sesión y cerrar sesión
 */
@Singleton
class GoogleAuthClient @Inject constructor(
    private val context: Context,
    private val clientId: String
) {
    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    /**
     * Obtiene el intent para iniciar el flujo de inicio de sesión con Google
     * @return Intent para iniciar la actividad de Google Sign-In
     */
    fun getSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    /**
     * Cierra la sesión actual del usuario con Google
     */
    suspend fun signOut() {
        try {
            // Implementación manual que reemplaza await()
            suspendCancellableCoroutine { continuation ->
                googleSignInClient.signOut()
                    .addOnSuccessListener {
                        if (continuation.isActive) {
                            continuation.resume(Unit)
                        }
                    }
                    .addOnFailureListener { exception ->
                        if (continuation.isActive) {
                            continuation.resumeWithException(exception)
                        }
                    }
            }
        } catch (e: Exception) {
            // Manejar error de cierre de sesión
        }
    }
}