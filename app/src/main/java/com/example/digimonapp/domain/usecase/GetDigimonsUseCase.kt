package com.example.digimonapp.domain.usecase

import com.example.digimonapp.data.DigimonRepository
import com.example.digimonapp.data.database.entities.toDatabase
import com.example.digimonapp.domain.models.Digimon
import javax.inject.Inject

class GetDigimonsUseCase @Inject constructor(private val repository: DigimonRepository) {
    suspend operator fun invoke(fromDatabase: Boolean = false): List<Digimon> {
        return if (fromDatabase) {
            repository.getAllDigimonsFromDatabase()
        } else {
            return repository.getDigimonsFromApi()
        }
        /*   return if (digimons.isNotEmpty()) {
               repository.clearDigimons()
               repository.insertDigimons(digimons.map { it.toDatabase() })
               digimons
           } else {
               repository.getAllDigimonsFromDatabase()
           }*/
    }
}