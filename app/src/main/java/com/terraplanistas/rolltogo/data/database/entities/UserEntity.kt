package com.terraplanistas.rolltogo.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class UserEntity(
    @PrimaryKey val id: String,
    val username: String,
    val user_image_url: String,
    val email: String,
    val created_at: String
)