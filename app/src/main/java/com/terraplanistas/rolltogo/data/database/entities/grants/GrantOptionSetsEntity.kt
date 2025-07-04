package com.terraplanistas.rolltogo.data.database.entities.grants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity

@Entity(
    tableName = "grant_option_sets",

)
data class GrantOptionSetsEntity(
    @PrimaryKey val id: String,
    val granter_content_id: String,
    val min_choices: Int,
    val max_choices: Int,
)