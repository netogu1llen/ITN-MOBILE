package com.app.soffyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.app.soffyapp.data.local.entity.PacienteEntity

@Dao
interface PacienteDao {

    @Query("SELECT * FROM pacientes")
    suspend fun getAllPacientes(): List<PacienteEntity>

    @Query("SELECT * FROM pacientes WHERE idExpediente = :id")
    suspend fun getPacienteById(id: Int): PacienteEntity?
}
