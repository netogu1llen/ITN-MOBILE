package com.app.soffyapp.domain.model

data class Paciente(
    val idExpediente: Int,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val fechaNacimiento: String,
    val telefono: String,
    val estado: String,
    val ciudad: String,
    val calle: String,
    val codigoPostal: String,
    val localidad: String,
    val numeroCasa: String,
    val enfermedades: String,
    val medicamentos: String,
    val estudioSocioeconomico: String,
    val tipoSangre: String,
    val grado: String,
    val nivelEscolar: String
) {
    companion object {
        fun getMockData(): List<Paciente> =
            listOf(
                Paciente(
                    idExpediente = 1,
                    nombre = "Diego",
                    apellidoPaterno = "Ponce de León",
                    apellidoMaterno = "Betanzos",
                    fechaNacimiento = "2003-12-20",
                    telefono = "5541850381",
                    estado = "Querétaro",
                    ciudad = "Querétaro",
                    calle = "Epigmenio",
                    codigoPostal = "14330",
                    localidad = "San Pablo",
                    numeroCasa = "500",
                    enfermedades = "Ninguna",
                    medicamentos = "Ninguno",
                    estudioSocioeconomico = "Medio",
                    tipoSangre = "A+",
                    grado = "3",
                    nivelEscolar = "Secundaria"
                ),
                Paciente(
                    idExpediente = 2,
                    nombre = "Sara",
                    apellidoPaterno = "Herrera",
                    apellidoMaterno = "Maldonado",
                    fechaNacimiento = "2003-04-22",
                    telefono = "4421234567",
                    estado = "Querétaro",
                    ciudad = "Querétaro",
                    calle = "Av. Universidad",
                    codigoPostal = "76000",
                    localidad = "Centro",
                    numeroCasa = "101",
                    enfermedades = "Ninguna",
                    medicamentos = "Ninguno",
                    estudioSocioeconomico = "Medio",
                    tipoSangre = "O+",
                    grado = "6",
                    nivelEscolar = "Secundaria"
                ),
                // Puedes agregar más si necesitas
            )
    }
}
