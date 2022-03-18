package com.example.digimonapp.data.database.dao

import androidx.room.*
import com.example.digimonapp.data.database.entities.DigimonEntity

@Dao
interface DigimonDao {
    @Query("SELECT * FROM " + DigimonEntity.TABLE_NAME + " ORDER BY name")
    suspend fun getDigimons():List<DigimonEntity>

    @Insert
    fun insert(contact: DigimonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<DigimonEntity>)

    @Delete
    fun delete(vararg contact: DigimonEntity)

    @Query("DELETE FROM "+ DigimonEntity.TABLE_NAME)
    suspend fun deleteAllDigimons()
}