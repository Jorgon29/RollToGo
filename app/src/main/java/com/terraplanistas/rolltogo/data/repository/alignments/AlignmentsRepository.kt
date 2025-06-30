package com.terraplanistas.rolltogo.data.repository.alignments

import android.content.Context
import com.terraplanistas.rolltogo.data.model.CharacterAlignment

interface AlignmentsRepository {
    fun getAlignments(context: Context): List<CharacterAlignment>
    fun getDefaultAlignments(context: Context): List<CharacterAlignment>
}
