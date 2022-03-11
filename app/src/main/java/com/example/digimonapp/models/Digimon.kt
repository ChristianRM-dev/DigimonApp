package com.example.digimonapp.models

import android.net.Uri

data class Digimon(var name: String, var img: String, var leler: DigimonLevel, var isFavorite: Boolean){

}

enum class DigimonLevel(val value: String) {
    Armor("Armor"),
    Champion("Champion"),
    Fresh("Fresh"),
    InTraining("In Training"),
    Mega("Mega"),
    Rookie("Rookie"),
    Training("Training"),
    Ultimate("Ultimate");

    companion object {
        public fun fromValue(value: String): DigimonLevel = when (value) {
            "Armor" -> Armor
            "Champion" -> Champion
            "Fresh" -> Fresh
            "In Training" -> InTraining
            "Mega" -> Mega
            "Rookie" -> Rookie
            "Training" -> Training
            "Ultimate" -> Ultimate
            else -> throw IllegalArgumentException()
        }
    }
}

object DigimonTest {
    // Will contain the 'favorite' marked cities to be displayed in FavoriteFragment RecyclerView
    var favoriteDigimonList: MutableList<Digimon> = mutableListOf()
}