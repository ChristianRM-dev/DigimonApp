package com.example.digimonapp.ui.listeners

import com.example.digimonapp.domain.models.Digimon

interface DigimonListListener {
    fun onItemClick(digimon: Digimon)
}