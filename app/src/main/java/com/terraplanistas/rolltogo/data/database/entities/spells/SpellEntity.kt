package com.terraplanistas.rolltogo.data.database.entities.spells

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.items.toDomainItem
import com.terraplanistas.rolltogo.data.enums.CastingTimeUnitEnum
import com.terraplanistas.rolltogo.data.enums.DurationUnitEnum
import com.terraplanistas.rolltogo.data.enums.RangeUnitEnum
import com.terraplanistas.rolltogo.data.enums.SpellLevelEnum
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
    val spell_level_enum: SpellLevelEnum,
    val spell_school_enum: SpellSchoolEnum,
    val casting_time_value: Int,
    val casting_time_unit_enum: CastingTimeUnitEnum,
    val range_value: Int,
    val range_unit_enum: RangeUnitEnum,
    val duration_value: Int,
    val duration_time_unit_enum: DurationUnitEnum,
    val is_ritual: Boolean
)

suspend fun SpellEntity.toDomainSpell(
    materialEntities: List<SpellMaterialEntity>,
    itemDao: ItemDao,
    grantId: String
): DomainSpell {
    val materialComponentsList = materialEntities.mapNotNull { spellMaterial ->
        val itemEntity = itemDao.getItemById(spellMaterial.item_id).firstOrNull()
        itemEntity?.let {
            val domainItem = it.toDomainItem(emptyList(), grantId)
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
        level = this.spell_level_enum,
        isRitual = is_ritual,
        grantId = grantId
    )
}