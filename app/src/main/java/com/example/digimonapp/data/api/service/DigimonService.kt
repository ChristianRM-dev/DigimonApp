package com.example.digimonapp.data.api.service

import com.example.digimonapp.data.api.client.DigimonApiClient
import com.example.digimonapp.data.api.models.DigimonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DigimonService @Inject constructor(private val api: DigimonApiClient) {

    suspend fun getDigimons(): List<DigimonModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getDigimons()
            response.body() ?: emptyList()
        }
    }

}