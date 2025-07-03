package com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.spellStrategy

import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy

class SpellStrategy: ContentStrategy {
    override fun validateContent(data: Map<String, Any>): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun sumbit(
        data: Map<String, Any>,
        repo: ContentCreationRepository
    ) {
        TODO("Not yet implemented")
    }

    override fun getDefaultData(): Map<String, Any> {
        TODO("Not yet implemented")
    }

}