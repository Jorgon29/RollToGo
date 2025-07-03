package com.terraplanistas.rolltogo.data.database.entities.features

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "features",

)
data class FeaturesEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val is_magical: Boolean,
    val is_passive: Boolean
)