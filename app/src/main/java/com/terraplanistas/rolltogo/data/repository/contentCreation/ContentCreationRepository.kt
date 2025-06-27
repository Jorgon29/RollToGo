package com.terraplanistas.rolltogo.data.repository.contentCreation

import com.terraplanistas.rolltogo.data.enums.SourceContentEnum

interface ContentCreationRepository {
    fun createContent(content: Map<String, Any>, type: SourceContentEnum)
}