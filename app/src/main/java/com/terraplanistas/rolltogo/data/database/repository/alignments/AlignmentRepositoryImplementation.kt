package com.terraplanistas.rolltogo.data.database.repository.alignments

import android.content.Context
import com.terraplanistas.rolltogo.data.defaults.getDefaultAlignments
import com.terraplanistas.rolltogo.data.model.CharacterAlignment

class AlignmentRepositoryImplementation(
    private val context: Context
): AlignmentsRepository {
    override fun getAlignments(): List<CharacterAlignment> {
        return getDefaultAlignments(context)
    }
}