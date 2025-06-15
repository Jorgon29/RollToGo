package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainAbility

@Entity(
    tableName = "abilities",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class AbilitiesEntity(
    @PrimaryKey val id: String,
    val value: Int,
    val modifier: Int,
    val ability_enum: AbilityTypeEnum
)

fun AbilitiesEntity.toDomainAbility(grantId: String): DomainAbility{
    return DomainAbility(
        id = id,
        abilityEnum = ability_enum,
        modifier = modifier,
        grantId = grantId
    )
}