package com.terraplanistas.rolltogo.data.database.entities.classEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.SpellcastingProgressionEnum
import com.terraplanistas.rolltogo.helpers.typeConverter.EnumConverters

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
    val spell_progression_enum: SpellcastingProgressionEnum,
    val preparation_formula: String,
    val spell_casting_ability: AbilityTypeEnum
)