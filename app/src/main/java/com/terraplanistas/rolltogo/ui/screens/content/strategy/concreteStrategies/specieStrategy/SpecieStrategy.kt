package com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.specieStrategy

import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy

class SpecieStrategy: ContentStrategy {
    override fun validateContent(data: Map<String, Any>): Boolean {
        TODO("Not yet implemented")
    }

    override fun sumbit(
        data: Map<String, Any>,
        repo: ContentCreationRepository
    ) {
        TODO("Not yet implemented")
    }

    override fun getDefaultData(): Map<String, Any> {
        TODO("Not yet implemented")
    }
}