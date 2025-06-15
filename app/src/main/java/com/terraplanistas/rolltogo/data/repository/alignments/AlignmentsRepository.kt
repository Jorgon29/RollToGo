package com.terraplanistas.rolltogo.data.repository.alignments

import com.terraplanistas.rolltogo.data.model.CharacterAlignment

interface AlignmentsRepository {

    fun getAlignments(): List<CharacterAlignment>
}