package com.app.soffyapp.domain.model

data class Expediente(
    val idExpediente: String,
    val nombreCompleto: String,
    val fechaNacimiento: String,
    val contacto: String,
    val estado: String,
    val ciudad: String,
    val calle: String,
    val cp: String,
    val localidad: String,
    val numCasa: String,
    val enfermedades: String,
    val medicamentos: String,
    val estudioSocioeconomico: String,
    val grado: String,
    val nvEscolar: String,
    val sangre: String,
)
