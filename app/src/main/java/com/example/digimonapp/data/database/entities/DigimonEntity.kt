package com.example.digimonapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digimonapp.core.config.di.Constants
import com.example.digimonapp.domain.models.Digimon
import org.jetbrains.annotations.NotNull

@Entity(tableName = DigimonEntity.TABLE_NAME)
data class DigimonEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "img") @NotNull val img: String,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
        ){
    companion object {
        const val TABLE_NAME = Constants.TABLE_DIGIMONS
    }

/*    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0*/
}

fun Digimon.toDatabase() = DigimonEntity(id,name, img,isFavorite)