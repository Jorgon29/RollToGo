package com.terraplanistas.rolltogo.data.repository.contentCreation

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
import com.terraplanistas.rolltogo.data.database.entities.features.FeaturesEntity
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import kotlinx.coroutines.flow.Flow
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

   //Proximamente
    }

    override suspend fun getFeatures(): List<FeaturesEntity> {
        return featuresDao.getAllFeatures().firstOrNull() ?: emptyList<FeaturesEntity>()

    }

}