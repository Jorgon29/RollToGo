package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum
import com.terraplanistas.rolltogo.data.enums.SavingThrowEnum

@Entity(
    tableName = "actions",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class ActionsEntity(
    @PrimaryKey val id: String,
    val action_type_enum: ActionTypeEnum,
    val attact_formula: String,
    val save_ability_enum: SavingThrowEnum,
    val save_dc_formula: String,
    val is_rolled: Boolean,
    val actions_source_enum: String
)