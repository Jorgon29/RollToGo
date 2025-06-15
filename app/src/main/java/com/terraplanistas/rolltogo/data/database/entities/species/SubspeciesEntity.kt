package com.terraplanistas.rolltogo.data.database.entities.species

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum

@Entity(
    tableName = "subspecies",
    foreignKeys = [
        ForeignKey(
            entity = SpeciesEntity::class,
            childColumns = ["species_id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class SubspeciesEntity(
    @PrimaryKey val id: String,
    val species_id: String,
    val name: String,
    val description: String,
    val languages: String,
    val size_enum: CreatureSizeEnum
)