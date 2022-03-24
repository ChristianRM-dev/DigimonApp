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

    suspend fun getDigimonFromApi(): List<Digimon> {
        val response: List<DigimonModel> = service.getDigimons()
        return response.map { it.toDomain() }
    }

    suspend fun getAllDigimonFromDatabase():List<Digimon>{
        val response: List<DigimonEntity> = dao.getDigimons()
        return response.map { it.toDomain() }
    }

    suspend fun markDigimonAsFavorite(digimon:DigimonEntity){
        dao.update(digimon)
    }

    suspend fun removeFavoriteDigimon(digimon:DigimonEntity){
        dao.delete(digimon)
    }

    suspend fun insertDigimonList(quotes:List<DigimonEntity>){
        dao.insertAll(quotes)
    }

    suspend fun clearDigimon(){
        dao.deleteAllDigimons()
    }

    suspend fun getFavoriteDigimon():List<Digimon>{
        val response: List<DigimonEntity> = dao.getFavoriteDigimons()
        return response.map { it.toDomain() }
    }
}