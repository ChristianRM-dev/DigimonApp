package com.example.digimonapp.models

import android.net.Uri

class Digimon(var name: String, var img: Uri, var leler: DigimonLevel, var isFavorite: Boolean){

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
    var digimonList: ArrayList<Digimon>? = null
        get() {
            if (field != null)      // backing 'field' refers to 'cityList' property object
                return field
            field = ArrayList()
            val digimon = Digimon(
                "Koromon",
                Uri.parse("https://digimon.shadowsmith.com/img/koromon.jpg"),
                DigimonLevel.InTraining,
                true
            )
            field?.add(digimon)
            return field
        }
    // Will contain the 'favorite' marked cities to be displayed in FavoriteFragment RecyclerView
    var favoriteDigimonList: MutableList<Digimon> = mutableListOf()
}