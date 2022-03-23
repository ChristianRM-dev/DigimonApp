package com.example.digimonapp.domain.usecase

import com.example.digimonapp.data.DigimonRepository
import com.example.digimonapp.data.database.entities.toDatabase
import com.example.digimonapp.domain.models.Digimon
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(private val repository: DigimonRepository) {
    suspend operator fun invoke(digimon: Digimon) {
        val digimonEntity = digimon.toDatabase()
        repository.removeFavoriteDigimon(digimonEntity);
    }
}