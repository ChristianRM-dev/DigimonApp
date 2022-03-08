package com.example.digimonapp.data.repository

import com.example.digimonapp.data.service.DigimonService
import javax.inject.Inject

class DigimonRepository  @Inject constructor(private val apiService: DigimonService) {
    suspend fun getDigimons() = apiService.getDigimons()
}