package com.example.digimonapp.data.api.models

import com.google.gson.annotations.SerializedName

data class DigimonModel(
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
    @SerializedName("level") val level: String
)