package com.terraplanistas.rolltogo.data.database.entities.spells

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum

@Entity(
    tableName = "spell",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class SpellEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val spell_components: String,
    val spell_level_enum: Int,
    val spell_school_enum: SpellSchoolEnum,
    val preparation_mode_enum: String,
    val casting_time_value: String,
    val casting_time_unit_enum: String,
    val range_value: String,
    val range_unit_enum: RangeUnitEnum,
    val duration_value: String,
    val duration_time_unit_enum: DurationUnitEnum
)