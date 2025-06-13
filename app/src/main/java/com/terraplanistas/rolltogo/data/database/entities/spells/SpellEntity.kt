package com.terraplanistas.rolltogo.data.database.entities.spells

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.items.toDomainItem
import com.terraplanistas.rolltogo.data.enums.SpellSchoolEnum
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpell
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainSpellMaterial
import kotlinx.coroutines.flow.firstOrNull

@Entity(
    tableName = "spell",
    foreignKeys = [
        ForeignKey(
            entity = ContentEntity::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class SpellEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val spell_components: String,
    val spell_level_enum: String,
    val spell_school_enum: SpellSchoolEnum,
    val casting_time_value: String,
    val casting_time_unit_enum: String,
    val range_value: String,
    val range_unit_enum: String,
    val duration_value: String,
    val duration_time_unit_enum: String
)

suspend fun SpellEntity.toDomainSpell(
    materialEntities: List<SpellMaterialEntity>,
    itemDao: ItemDao
): DomainSpell {
    val materialComponentsList = materialEntities.mapNotNull { spellMaterial ->
        val itemEntity = itemDao.getItemById(spellMaterial.item_id).firstOrNull()
        itemEntity?.let {
            val domainItem = it.toDomainItem(emptyList())
            DomainSpellMaterial(item = domainItem, consumed = spellMaterial.consumed)
        }
    }

    return DomainSpell(
        id = this.id,
        name = this.name,
        description = this.description,
        spellMaterials = materialComponentsList,
        components = this.spell_components,
        spellSchoolEnum = this.spell_school_enum,
        castingTime = "${this.casting_time_value} ${this.casting_time_unit_enum}",
        range = "${this.range_value} ${this.range_unit_enum}",
        duration = "${this.duration_value} ${this.duration_time_unit_enum}",
        level = this.spell_level_enum
    )
}