package com.terraplanistas.rolltogo.data.database.entities.classEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "subclass",
    foreignKeys = [
        ForeignKey(
            entity = ClassEntity::class,
            parentColumns = ["id"],
            childColumns = ["class_id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class SubclassEntity(
    @PrimaryKey val id: String,
    val class_id: String,
    val name: String,
    val description: String,
    val hit_points_first_level: Int,
    val hit_points_per_level: Int
)