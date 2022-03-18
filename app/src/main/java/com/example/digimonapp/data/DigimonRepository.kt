package com.example.digimonapp.data

import com.example.digimonapp.data.api.models.DigimonModel
import com.example.digimonapp.data.api.service.DigimonService
import com.example.digimonapp.data.database.dao.DigimonDao
import com.example.digimonapp.data.database.entities.DigimonEntity
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.domain.models.toDomain
import javax.inject.Inject

class DigimonRepository  @Inject constructor(
    private val service: DigimonService,
    private val dao: DigimonDao
    ) {

    suspend fun getDigimonsFromApi(): List<Digimon> {
        val response: List<DigimonModel> = service.getDigimons()
        return response.map { it.toDomain() }
    }

    suspend fun getAllDigimonsFromDatabase():List<Digimon>{
        val response: List<DigimonEntity> = dao.getDigimons()
        return response.map { it.toDomain() }
    }

    suspend fun insertDigimon(digimon:DigimonEntity){
        dao.insert(digimon)
    }

    suspend fun insertDigimons(quotes:List<DigimonEntity>){
        dao.insertAll(quotes)
    }

    suspend fun clearDigimons(){
        dao.deleteAllDigimons()
    }
}