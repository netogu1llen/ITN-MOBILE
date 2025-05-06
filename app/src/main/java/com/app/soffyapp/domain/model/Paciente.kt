package com.app.soffyapp.domain.model

data class Paciente(
    val idExpediente: String,
    val nombreCompleto: String,
    val fechaNacimiento: String,
    val nvEscolar: String,
) {
    companion object {
        fun getMockData(): List<Paciente> =
            listOf(
                Paciente(
                    idExpediente = "",
                    nombreCompleto = "Juan Perez Gomez",
                    fechaNacimiento = "2005-08-15",
                    nvEscolar = "Secundaria",
                ),
                Paciente(
                    idExpediente = "",
                    nombreCompleto = "Ana Ruiz Sanchez",
                    fechaNacimiento = "2006-11-20",
                    nvEscolar = "Secundaria",
                ),
                // Puedes agregar más si necesitas
            )
    }
}
