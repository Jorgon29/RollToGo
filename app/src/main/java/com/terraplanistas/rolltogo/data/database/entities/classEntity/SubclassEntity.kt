package com.terraplanistas.rolltogo.data.database.entities.classEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "subclass",

)
data class SubclassEntity(
    @PrimaryKey val id: String,
    val class_id: String,
    val name: String,
    val description: String,
)