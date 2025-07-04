package com.terraplanistas.rolltogo.data.repository.contentCreation

import android.icu.math.BigDecimal
import com.terraplanistas.rolltogo.data.database.dao.ContentDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.MonstersDao
import com.terraplanistas.rolltogo.data.database.dao.features.FeaturesDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantsDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemTagDao
import com.terraplanistas.rolltogo.data.database.dao.misc.BackgroundDao
import com.terraplanistas.rolltogo.data.database.dao.misc.DamagesDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellDao
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity
import com.terraplanistas.rolltogo.data.database.entities.items.ItemTagEntity
import com.terraplanistas.rolltogo.data.database.entities.misc.DamagesEntity
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import kotlinx.coroutines.flow.firstOrNull

class ContentCreationRepositoryImpi(

    val contentDao: ContentDao,
    val grantsDao: GrantsDao,
    val itemDao: ItemDao,
    val itemTagDao: ItemTagDao,
    val damagesDao: DamagesDao,
    val spellDao: SpellDao,
    val backgroundDao: BackgroundDao,
    val creaturesDao: CreaturesDao,
    val monstersDao: MonstersDao,
    val featuresDao: FeaturesDao

): ContentCreationRepository {

    override fun createContent(
        content: Map<String, Any>,
        type: SourceContentEnum
    ) {
        
    }

    override suspend fun getFeatures(): List<FeaturesEntity> {
        return featuresDao.getAllFeatures().firstOrNull() ?: emptyList<FeaturesEntity>()

    }

    private suspend fun createItem(itemData: Map<String, Any>) {
        // Crear el contenido base
        val contentId = contentDao.insertContent(
            ContentEntity(
                id = 0.toString(), // Auto-generado
                name = itemData["name"].toString(),
                description = itemData["description"].toString(),
                source = SourceContentEnum.ITEM.name,
                isHomebrew = true,
                isPublic = false,
                source_content_enum = TODO(),
                visibility_enum = TODO(),
                created_at = TODO(),
                author_id = TODO()
            )
        )

        // Crear el ítem
        val itemEntity = ItemEntity(
            id = 0.toString(),
            name = TODO(),
            description = TODO(),
            item_type_enum = TODO(),
            //weight = (itemData["weight"] as? BigDecimal)?.toDouble() ?: 0.0,

            rarity_enum = TODO(),
            cost_value = TODO(),
            cost_unit = TODO(),
            attunement_required = TODO(),
            is_magical = TODO(),
            contentId = contentId,
            itemType = itemData["item_type"].toString(),
            rarity = itemData["rarity"].toString(),
            costValue = (itemData["cost_value"] as? BigDecimal)?.toDouble() ?: 0.0,
            costUnit = itemData["cost_unit"].toString(),
            requiresAttunement = itemData["attunment"] as? Boolean ?: false,
            isMagical = itemData["is_magical"] as? Boolean ?: false,
            actionType = itemData["action_type"].toString(),
            weight1 = TODO(),
            weight = TODO()
        )
        val itemId = itemDao.insertItem(itemEntity)

        // Manejar el daño
        val damageEntity = DamagesEntity(
            id = 0.toString(),
            itemId = itemId,
            damageDice = itemData["damage"].toString(),
            damageType = itemData["damage_tipe"].toString(),
            damage_formula = TODO(),
            damage_type_enum = TODO(),
            repeat = TODO(),
            repetition_value = TODO(),
            repetition_unit = TODO()
        )
        damagesDao.insertDamage(damageEntity)

        // Manejar las etiquetas (tags)
        val tags = itemData["item_tag"] as? List<String> ?: emptyList()
        tags.forEach { tagName ->
            val tagEntity = ItemTagEntity(

                tag = tagName,
                id = 0,
                itemId = itemId,
                items_id = TODO()
            )
            itemTagDao.insertItemTag(tagEntity)
        }

        // Nota: Podrías añadir transacciones aquí para asegurar la atomicidad
    }
}