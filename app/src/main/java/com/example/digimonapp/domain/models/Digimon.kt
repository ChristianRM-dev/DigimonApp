package com.example.digimonapp.domain.models

import com.example.digimonapp.data.api.models.DigimonModel
import com.example.digimonapp.data.database.entities.DigimonEntity
import com.example.digimonapp.domain.models.enums.DigimonLevel

data class Digimon(
    var id: Int =0,
    var name: String,
    var img: String,
    var level: DigimonLevel,
    var isFavorite: Boolean
) {
}

fun DigimonModel.toDomain() = Digimon(0, name, img, DigimonLevel.Armor, false)
fun DigimonEntity.toDomain() = Digimon(id, name, img, DigimonLevel.Armor, isFavorite)

object DigimonTest {
    // Will contain the 'favorite' marked cities to be displayed in FavoriteFragment RecyclerView
    var favoriteDigimonList: MutableList<Digimon> = mutableListOf()
}