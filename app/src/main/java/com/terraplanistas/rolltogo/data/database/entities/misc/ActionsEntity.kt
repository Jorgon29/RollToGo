package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum

@Entity(
    tableName = "actions",

)
data class ActionsEntity(
    @PrimaryKey val id: String,
    val action_type_enum: ActionTypeEnum,
    val attact_formula: String,
    val save_ability_enum: AbilityTypeEnum,
    val save_dc_formula: String,
    val is_rolled: Boolean,
)