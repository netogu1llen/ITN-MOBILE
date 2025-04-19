package com.app.soffyapp.domain.model

data class Paciente(
    val idExpediente: Int,
    val nombreCompleto: String,
    val fechaNacimiento: String,
    val nvEscolar: String,
) {
    companion object {
        fun getMockData(): List<Paciente> =
            listOf(
                Paciente(
                    idExpediente = 1,
                    nombreCompleto = "Juan Perez Gomez",
                    fechaNacimiento = "2005-08-15",
                    nvEscolar = "Secundaria",
                ),
                Paciente(
                    idExpediente = 2,
                    nombreCompleto = "Ana Ruiz Sanchez",
                    fechaNacimiento = "2006-11-20",
                    nvEscolar = "Secundaria",
                ),
                // Puedes agregar más si necesitas
            )
    }
}
