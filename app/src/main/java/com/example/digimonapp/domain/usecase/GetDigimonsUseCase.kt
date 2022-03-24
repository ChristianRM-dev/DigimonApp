package com.example.digimonapp.domain.usecase

import com.example.digimonapp.data.DigimonRepository
import com.example.digimonapp.data.database.entities.toDatabase
import com.example.digimonapp.domain.models.Digimon
import javax.inject.Inject

class GetDigimonsUseCase @Inject constructor(private val repository: DigimonRepository) {
    suspend operator fun invoke(onlyFavorites: Boolean = false): List<Digimon> {
        /* return if (fromDatabase) {
             repository.getAllDigimonsFromDatabase()
         } else {
             return repository.getDigimonsFromApi()
         }*/

        return if (onlyFavorites) {
            repository.getFavoriteDigimon()
        } else {
            val digimons = repository.getAllDigimonFromDatabase()
            if (digimons.isNotEmpty()) {
                digimons
            } else {
                repository.clearDigimon()
                val apiDigimons = repository.getDigimonFromApi()
                repository.insertDigimonList(apiDigimons.map { it.toDatabase() })
                repository.getAllDigimonFromDatabase()
            }
        }
    }
}