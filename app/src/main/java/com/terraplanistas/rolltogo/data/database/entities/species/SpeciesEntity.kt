package com.terraplanistas.rolltogo.data.database.entities.species

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import com.terraplanistas.rolltogo.data.enums.SizeEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainRace

@Entity(
    tableName = "species",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            childColumns = ["id"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class SpeciesEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val creature_type_enum: CreatureTypeEnum,
    val languages: String,
    val size_enum: SizeEnum
)


fun SpeciesEntity.toDomainRace(): DomainRace {
    return DomainRace(
        id = this.id,
        name = this.name,
        description = this.description,
        languages = this.languages,
        size = this.size_enum
    )
}
