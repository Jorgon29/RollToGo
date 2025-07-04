package com.terraplanistas.rolltogo.ui.screens.content.strategy

import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository

interface ContentStrategy {
    fun validateContent(data: Map<String, Any>): Boolean
    suspend fun sumbit(data: Map<String, Any>, repo: ContentCreationRepository)
    fun getDefaultData(): Map<String, Any>
}