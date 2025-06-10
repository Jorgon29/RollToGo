package com.terraplanistas.rolltogo.data.model

import com.terraplanistas.rolltogo.data.database.entities.FriendEntity

data class Friend(
    val id: Int,
    val name: String,
    val image: String
)

fun Friend.toEntity(): FriendEntity{
    return FriendEntity(
        this.id, this.name, this.image
    )
}