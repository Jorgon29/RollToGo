package com.terraplanistas.rolltogo.data.database.entities.features

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
        tableName = "special_die",

)
data class SpecialDieEntity(
    @PrimaryKey val id: String,
    val feature_id: String,
    val name: String,
    val quantity: Int,
    val die_formula: String
)