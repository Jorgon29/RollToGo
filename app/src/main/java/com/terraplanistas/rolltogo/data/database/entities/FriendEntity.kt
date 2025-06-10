package com.terraplanistas.rolltogo.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.model.Friend

@Entity(tableName = "friend")
class FriendEntity (
    @PrimaryKey val id: Int,
    val name: String,
    val image: String
)

fun FriendEntity.toDomain(): Friend{
    return Friend(
        this.id, this.name, this.image
    )
}

