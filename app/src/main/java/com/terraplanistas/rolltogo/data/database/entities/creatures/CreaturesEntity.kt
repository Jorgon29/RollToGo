package com.terraplanistas.rolltogo.data.database.entities.creatures

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.AlignmentEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSizeEnum
import com.terraplanistas.rolltogo.data.enums.CreatureSourceType
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.helpers.typeConverter.EnumConverters

@Entity(
    tableName = "creatures",
    foreignKeys = [
        ForeignKey(
            entity = MonstersEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = CharactersEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        ),

    ]
)
data class CreaturesEntity(
    @PrimaryKey val id: String,
    val name: String,
    val size_enum: CreatureSizeEnum,
    val type_enum: CreatureTypeEnum,
    val alignment_enum: AlignmentEnum,
    val base_hp: Int,
    val base_ac: Int,
    val creature_source_type: CreatureSourceType
)