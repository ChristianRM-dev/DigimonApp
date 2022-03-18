package com.example.digimonapp.domain.models.enums

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