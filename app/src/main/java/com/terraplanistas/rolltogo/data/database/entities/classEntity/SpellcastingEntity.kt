package com.terraplanistas.rolltogo.data.database.entities.classEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.enums.SpellProgressionEnum

@Entity(
    tableName = "spellcasting",
    foreignKeys = [
        ForeignKey(
            entity = ClassEntity::class,
            childColumns = ["class_id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class SpellcastingEntity(
    @PrimaryKey val id: String,
    val class_id: String,
    val spell_progression_enum: SpellProgressionEnum,
    val preparation_formula: String
)