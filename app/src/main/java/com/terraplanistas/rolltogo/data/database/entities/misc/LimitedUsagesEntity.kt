package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.RecoveryTypeEnum

@Entity(
    tableName = "limited_usages",

)
data class LimitedUsagesEntity(
    @PrimaryKey val id: String,
    val base_max_uses_formula: String,
    val is_scaling: Boolean,
    val uses: Int,
    val scaling_formula: String,
    val recovery_type_enum: RecoveryTypeEnum
)