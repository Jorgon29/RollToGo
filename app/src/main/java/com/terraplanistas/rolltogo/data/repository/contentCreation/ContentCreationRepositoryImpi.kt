package com.terraplanistas.rolltogo.data.repository.contentCreation

import com.terraplanistas.rolltogo.data.database.dao.ContentDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.CreaturesDao
import com.terraplanistas.rolltogo.data.database.dao.creatures.MonstersDao
import com.terraplanistas.rolltogo.data.database.dao.grants.GrantsDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemDao
import com.terraplanistas.rolltogo.data.database.dao.items.ItemTagDao
import com.terraplanistas.rolltogo.data.database.dao.misc.BackgroundDao
import com.terraplanistas.rolltogo.data.database.dao.misc.DamagesDao
import com.terraplanistas.rolltogo.data.database.dao.spells.SpellDao
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum

class ContentCreationRepositoryImpi(

    contentDao: ContentDao,
    grantsDao: GrantsDao,
    itemDao: ItemDao,
    itemTagDao: ItemTagDao,
    damagesDao: DamagesDao,
    spellDao: SpellDao,
    backgroundDao: BackgroundDao,
    creaturesDao: CreaturesDao,
    monstersDao: MonstersDao,

): ContentCreationRepository {

    override fun createContent(
        content: Map<String, Any>,
        type: SourceContentEnum
    ) {
        TODO("Not yet implemented")
    }

}