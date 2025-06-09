package com.terraplanistas.rolltogo.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alignment")
class AlignmentEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String
)