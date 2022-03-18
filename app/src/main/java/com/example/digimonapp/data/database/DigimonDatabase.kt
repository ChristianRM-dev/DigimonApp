package com.example.digimonapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digimonapp.data.database.dao.DigimonDao
import com.example.digimonapp.data.database.entities.DigimonEntity

@Database(entities = [DigimonEntity::class], version = 1)
abstract  class DigimonDatabase : RoomDatabase(){
    abstract fun getDigimonDao():DigimonDao
}