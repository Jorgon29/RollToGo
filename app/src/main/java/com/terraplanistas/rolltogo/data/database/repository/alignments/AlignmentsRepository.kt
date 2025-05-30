package com.terraplanistas.rolltogo.data.database.repository.alignments

import com.terraplanistas.rolltogo.data.model.CharacterAlignment

interface AlignmentsRepository {

    fun getAlignments(): List<CharacterAlignment>
}