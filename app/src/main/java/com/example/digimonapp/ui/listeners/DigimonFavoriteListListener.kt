package com.example.digimonapp.ui.listeners

import com.example.digimonapp.domain.models.Digimon

interface DigimonFavoriteListListener {
    fun onRemoveFavorite(digimon: Digimon)
}