package com.example.digimonapp.data.service

import com.example.digimonapp.models.Digimon
import retrofit2.Response
import retrofit2.http.GET

interface DigimonService {
    @GET("digimon")
    suspend fun getDigimons(): Response<ArrayList<Digimon>>
}