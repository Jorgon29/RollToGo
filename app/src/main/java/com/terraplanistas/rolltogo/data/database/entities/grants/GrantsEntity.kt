package com.terraplanistas.rolltogo.data.database.entities.grants

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.helpers.typeConverter.EnumConverters
import java.util.UUID
import kotlin.uuid.Uuid

@Entity(
    tableName = "grants",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["granter_content_id"],
        ),
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["granted_content_id"],
        )
    ]
)
data class GrantsEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val granter_type_enum: SourceContentEnum,
    val granter_content_id: String,
    val granted_type: SourceContentEnum,
    val granted_content_id: String
)