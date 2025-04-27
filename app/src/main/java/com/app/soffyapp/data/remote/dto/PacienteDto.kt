package com.app.soffyapp.data.remote.dto

data class PacienteDto(
    val idExpediente: Int?,
    val nombre: String?,
    val apellidoPaterno: String?,
    val apellidoMaterno: String?,
    val fechaNacimiento: String?,
    val telefono: String?,
    val estado: String?,
    val ciudad: String?,
    val calle: String?,
    val codigoPostal: String?,
    val localidad: String?,
    val numeroCasa: String?,
    val enfermedades: String?,
    val medicamentos: String?,
    val estudioSocioeconomico: String?,
    val tipoSangre: String?,
    val grado: String?,
    val nivelEscolar: String?
)
