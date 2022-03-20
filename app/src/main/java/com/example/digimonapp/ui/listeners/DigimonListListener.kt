package com.example.digimonapp.ui.listeners

import com.example.digimonapp.domain.models.Digimon

interface DigimonListListener {
    fun onFavoriteClick(digimon: Digimon)
}