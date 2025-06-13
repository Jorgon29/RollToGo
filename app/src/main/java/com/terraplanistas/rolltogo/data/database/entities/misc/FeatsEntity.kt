package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.model.character.DomainFeats

@Entity(
    tableName = "feats",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"]
        )
    ]
)
data class FeatsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String
)


fun FeatsEntity.toDomainFeats(): DomainFeats {
    return DomainFeats(
        id = this.id,
        name = this.name,
        description = this.description
    )
}