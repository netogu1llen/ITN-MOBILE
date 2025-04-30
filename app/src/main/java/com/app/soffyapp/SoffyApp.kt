package com.app.soffyapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Clase de aplicación principal para SoffyApp.
 *
 * La anotación @HiltAndroidApp es necesaria para:
 * 1. Inicializar el componente Hilt para toda la aplicación
 * 2. Generar el código necesario para la inyección de dependencias
 * 3. Servir como contenedor de dependencias a nivel de aplicación
 */
@HiltAndroidApp
class SoffyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Aquí puedes inicializar componentes que necesiten el contexto de la aplicación
        // Por ejemplo: bibliotecas de análisis, gestores de caché, etc.
    }
}