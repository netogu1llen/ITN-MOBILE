package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.remote.dto.NutricionalDataDto
import com.app.soffyapp.data.remote.dto.NutricionalListDto
import com.app.soffyapp.domain.model.*

fun NutricionalListDto.toDomainList(): List<NutricionalData> =
    data.map { it.toDomain() }

fun NutricionalDataDto.toDomain(): NutricionalData {
    return NutricionalData(
        nutricional = this.nutricional?.let {
            Nutricional(
                it.numSesion,
                it.diabetes,
                it.cancer,
                it.dislipidemia,
                it.obesidad,
                it.anemia,
                it.hipertensionArterial,
                it.pesoNacer,
                it.tallaNacer,
                it.alimentacionRecibida,
                it.sdg,
                it.tipoParto,
                it.complicaciones,
                it.lactancia,
                it.tiempo,
                it.edadAlimentacionComplementaria,
                it.alimentosPrimerAnio
            )
        },
        clinicos = this.indicadoresClinicos?.let {
            IndicadoresClinicos(
                it.cabello,
                it.dientes,
                it.piel,
                it.unias,
                it.conjunto,
                it.boca,
                it.edema
            )
        },
        transtornos = this.transtornos?.let {
            Transtornos(
                it.reflujo,
                it.vomito,
                it.disfagia,
                it.diarrea,
                it.flatulencias,
                it.estrenimiento,
                it.distencion,
                it.colitis,
                it.pirosis,
                it.gastritis,
                it.otro
            )
        },
        actividad = this.actividadDiaria?.let {
            ActividadDiaria(
                it.ejercicioFisico,
                it.frecuencia
            )
        },
        bioquim = this.indicadoresBioquim?.let {
            IndicadoresBioquim(
                it.parametro,
                it.valorReferencia
            )
        },
        antropometria = this.evaluacionAntropometrica?.let {
            EvaluacionAntropometrica(
                it.talla,
                it.edad,
                it.peso,
                it.circunferenciaCintura,
                it.circunferenciaCadera
            )
        },
        diagnostico = this.diagnosticoEvolucion?.diagnosticoEvolucion,
        objetivo = this.objetivoNutricional?.objetivo,
        manejo = this.manejoNutricional?.let {
            ManejoNutricional(
                it.energia,
                it.hidratosDeCarbono,
                it.lipidos,
                it.proteinas,
                it.fibra,
                it.agua
            )
        }
    )
}
