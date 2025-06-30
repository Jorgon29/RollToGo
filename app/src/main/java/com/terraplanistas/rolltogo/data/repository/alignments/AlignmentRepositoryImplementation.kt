package com.terraplanistas.rolltogo.data.repository.alignments

import javax.inject.Inject
import com.terraplanistas.rolltogo.data.model.CharacterAlignment
import com.terraplanistas.rolltogo.data.remote.services.AlignmentService
import com.terraplanistas.rolltogo.data.repository.alignments.AlignmentsRepository

class AlignmentRepositoryImplementation @Inject constructor(
    private val alignmentService: AlignmentService,
    private val localDataSource: AlignmentDao
) : AlignmentsRepository, BaseRepository() {

    override suspend fun getAlignments(refresh: Boolean): List<CharacterAlignment> {
        return if (refresh) {
            safeApiCall(
                apiCall = {
                    alignmentService.getAlignments()
                        .map { it.toCharacterAlignment() }
                        .also { localDataSource.insertAll(it) }
                },
                localCall = { localDataSource.getAll() }
            )
        } else {
            localDataSource.getAll().ifEmpty {
                safeApiCall(
                    apiCall = {
                        alignmentService.getAlignments()
                            .map { it.toCharacterAlignment() }
                            .also { localDataSource.insertAll(it) }
                    },
                    localCall = { emptyList() }
                )
            }
        }
    }
}
