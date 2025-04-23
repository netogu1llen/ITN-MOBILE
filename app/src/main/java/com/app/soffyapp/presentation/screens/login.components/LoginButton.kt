package com.app.soffyapp.presentation.screens.login.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

/**
 * Componente de botón para autenticación con Google
 *
 * Este componente:
 * - Muestra un botón de inicio de sesión con Google
 * - Maneja el flujo de autenticación nativo de Google
 * - Proporciona el token ID al componente padre cuando el login es exitoso
 *
 * @param onTokenReceived Callback que recibe el token ID de Google cuando el login es exitoso
 */
@Composable
fun GoogleLoginButton(onTokenReceived: (String) -> Unit) {
    // Obtiene el contexto actual de la aplicación
    val context = LocalContext.current

    // Configura el lanzador para el resultado de la actividad de login
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Procesa el resultado del intent de login
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            // Obtiene la cuenta de Google
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken

            // Si se obtuvo un token válido, lo pasa al callback
            if (idToken != null) {
                onTokenReceived(idToken)
            }
        } catch (e: ApiException) {
            // Manejo de errores durante el login
            Log.e("GoogleSignIn", "Error en el login", e)
        }
    }

    // Botón que inicia el flujo de autenticación
    Button(
        onClick = {
            // Configura las opciones de login con Google
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("TU_CLIENT_ID_WEB") // Reemplazar con client ID real
                .requestEmail() // Solicita el email del usuario
                .build()

            // Crea el cliente de Google SignIn
            val googleSignInClient = GoogleSignIn.getClient(context, gso)

            // Obtiene el intent de login y lo lanza
            val signInIntent = googleSignInClient.signInIntent
            launcher.launch(signInIntent)
        }
    ) {
        // Texto del botón
        Text("Iniciar sesión con Google")
    }
}