package com.example.digimonapp.data.api.client

import com.example.digimonapp.data.api.models.DigimonModel
import com.example.digimonapp.domain.models.Digimon
import retrofit2.Response
import retrofit2.http.GET

interface DigimonApiClient {
    @GET("digimon")
    suspend fun getDigimons(): Response<List<DigimonModel>>
}