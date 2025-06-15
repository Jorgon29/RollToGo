package com.terraplanistas.rolltogo.data.database.entities.misc

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainFeats

@Entity(
    tableName = "feats",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class FeatsEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String
)


fun FeatsEntity.toDomainFeats(grantId: String): DomainFeats {
    return DomainFeats(
        id = this.id,
        name = this.name,
        description = this.description,
        grantId = grantId
    )
}