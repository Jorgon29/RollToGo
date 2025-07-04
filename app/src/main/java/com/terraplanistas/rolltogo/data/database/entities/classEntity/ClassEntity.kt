package com.terraplanistas.rolltogo.data.database.entities.classEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.classEntity.ClassEntity

@Entity(
    tableName = "class",

)
data class ClassEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val hit_dice: String,
    val hit_points_first_level: Int,
    val hit_points_per_level: String
)