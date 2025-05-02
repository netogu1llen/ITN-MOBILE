package com.app.soffyapp.data.remote.dto

data class NutricionalResultDto(
    val success: Boolean,
    val data: NutricionalDataDto
)

data class NutricionalDataDto(
    val nutricional: NutricionalDto?,
    val indicadoresClinicos: IndicadoresClinicosDto?,
    val transtornos: TranstornosDto?,
    val actividadDiaria: ActividadDiariaDto?,
    val indicadoresBioquim: IndicadoresBioquimDto?,
    val evaluacionAntropometrica: EvaluacionAntropometricaDto?,
    val diagnosticoEvolucion: DiagnosticoEvolucionDto?,
    val objetivoNutricional: ObjetivoNutricionalDto?,
    val manejoNutricional: ManejoNutricionalDto?
)

data class NutricionalDto(
    val IDNutricional1: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String?,
    val diabetes: String,
    val cancer: String,
    val dislipidemia: String,
    val obesidad: String,
    val anemia: String,
    val hipertensionArterial: String,
    val pesoNacer: String,
    val tallaNacer: String,
    val alimentacionRecibida: String,
    val sdg: String,
    val tipoParto: String,
    val complicaciones: String,
    val lactancia: String,
    val tiempo: String,
    val edadAlimentacionComplementaria: String,
    val alimentosPrimerAnio: String,
    val eliminado: Int?
)

data class IndicadoresClinicosDto(
    val IDIndicadorClinico: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String,
    val cabello: String,
    val dientes: String,
    val piel: String,
    val unias: String,
    val conjunto: String,
    val boca: String,
    val edema: String,
    val eliminado: Int?
)

data class TranstornosDto(
    val IDTranstornos: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String,
    val reflujo: String,
    val vomito: String,
    val disfagia: String,
    val diarrea: String,
    val flatulencias: String,
    val estrenimiento: String,
    val distencion: String,
    val colitis: String,
    val pirosis: String,
    val gastritis: String,
    val otro: String,
    val eliminado: Int?
)

data class ActividadDiariaDto(
    val IDActividadDiaria: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String,
    val ejercicioFisico: String,
    val fechaInicio: String,
    val frecuencia: String,
    val eliminado: Int?
)

data class IndicadoresBioquimDto(
    val IDIndicadoresBioquim: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String?,
    val parametro: String,
    val valorReferencia: String,
    val parametroFecha: String,
    val eliminado: Int?
)

data class EvaluacionAntropometricaDto(
    val IDEvaluacionAntropometrica: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String,
    val talla: String,
    val edad: Int,
    val peso: String,
    val circunferenciaCintura: String,
    val circunferenciaCadera: String,
    val eliminado: Int?
)

data class DiagnosticoEvolucionDto(
    val IDDiagnosticoEvolucion: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String,
    val diagnosticoEvolucion: String,
    val eliminado: Int?
)

data class ObjetivoNutricionalDto(
    val IDObjetivoNutricional: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String?,
    val objetivo: String,
    val eliminado: Int?
)

data class ManejoNutricionalDto(
    val IDManejoNutricional: Int,
    val IDExpediente: Int,
    val numSesion: Int,
    val fecha: String,
    val energia: String,
    val hidratosDeCarbono: String,
    val lipidos: String,
    val proteinas: String,
    val fibra: String,
    val agua: String,
    val eliminado: Int?
)
