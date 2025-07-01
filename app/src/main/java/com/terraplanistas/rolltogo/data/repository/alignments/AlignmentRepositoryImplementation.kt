package com.terraplanistas.rolltogo.data.repository.alignments

import android.content.Context
import javax.inject.Inject
import com.terraplanistas.rolltogo.data.model.CharacterAlignment

class AlignmentRepositoryImplementation @Inject constructor(
    private val context: Context,
) : AlignmentsRepository {
    override fun getAlignments(): List<CharacterAlignment> {
        return com.terraplanistas.rolltogo.data.defaults.getDefaultAlignments(context)
    }

}