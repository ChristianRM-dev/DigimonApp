package com.example.digimonapp.domain.models

import com.example.digimonapp.data.DigimonRepository
import com.example.digimonapp.data.database.entities.toDatabase
import javax.inject.Inject

class GetDigimonsUseCase @Inject constructor(private val repository: DigimonRepository) {
    suspend operator fun invoke():List<Digimon>{
        val digimons = repository.getDigimonsFromApi()

        return if(digimons.isNotEmpty()){
            repository.clearDigimons()
            repository.insertDigimons(digimons.map { it.toDatabase() })
            digimons
        }else{
            repository.getAllDigimonsFromDatabase()
        }
    }
}