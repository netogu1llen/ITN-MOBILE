package com.app.soffyapp.domain.model

data class NutricionalData(
    val nutricional: Nutricional?,
    val clinicos: IndicadoresClinicos?,
    val transtornos: Transtornos?,
    val actividad: ActividadDiaria?,
    val bioquim: IndicadoresBioquim?,
    val antropometria: EvaluacionAntropometrica?,
    val diagnostico: String?,
    val objetivo: String?,
    val manejo: ManejoNutricional?
)

data class Nutricional(
    val numSesion: Int,
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
    val alimentosPrimerAnio: String
)

data class IndicadoresClinicos(
    val cabello: String,
    val dientes: String,
    val piel: String,
    val unias: String,
    val conjunto: String,
    val boca: String,
    val edema: String
)

data class Transtornos(
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
    val otro: String
)

data class ActividadDiaria(
    val ejercicioFisico: String,
    val frecuencia: String
)

data class IndicadoresBioquim(
    val parametro: String,
    val valorReferencia: String
)

data class EvaluacionAntropometrica(
    val talla: String,
    val edad: Int,
    val peso: String,
    val circunferenciaCintura: String,
    val circunferenciaCadera: String
)

data class ManejoNutricional(
    val energia: String,
    val hidratosDeCarbono: String,
    val lipidos: String,
    val proteinas: String,
    val fibra: String,
    val agua: String
)
