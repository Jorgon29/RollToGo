package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.terraplanistas.rolltogo.data.database.repository.BaseRepository
import com.terraplanistas.rolltogo.data.database.repository.settings.UserPreferencesRepository
import com.terraplanistas.rolltogo.data.database.repository.alignments.AlignmentRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.alignments.AlignmentsRepository
import com.terraplanistas.rolltogo.data.database.repository.classes.ClassesRepository
import com.terraplanistas.rolltogo.data.database.repository.classes.ClassesRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.genders.GendersRepository
import com.terraplanistas.rolltogo.data.database.repository.genders.GendersRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepositoryImplementation
import com.terraplanistas.rolltogo.data.database.repository.races.RaceRepository
import com.terraplanistas.rolltogo.data.database.repository.races.RaceRepositoryImplementation

private val USER_PREFERENCE_NAME = "user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

class AppProvider (context: Context){
    private val appDatabase: RollToGoDatabase = RollToGoDatabase.getDatabase(context)


    private val playstyleRepository: PlaystyleRepository = PlaystyleRepositoryImplementation(context)
    private val userPreferenceRepository: UserPreferencesRepository = UserPreferencesRepository(context.dataStore)
    private val classesRepository: ClassesRepository = ClassesRepositoryImplementation(context)
    private val racesRepository: RaceRepository = RaceRepositoryImplementation(context)
    private val alignmentsRepository: AlignmentsRepository = AlignmentRepositoryImplementation(context)
    private val gendersRepository: GendersRepository = GendersRepositoryImplementation(context)

    fun providePlaystyleRepository(): PlaystyleRepository {
        return playstyleRepository
    }

    fun provideUserPreferenceRepository(): UserPreferencesRepository {
        return userPreferenceRepository
    }

    fun provideClassesRepository(): ClassesRepository {
        return classesRepository
    }

    fun provideRacesRepository(): RaceRepository {
        return racesRepository
    }

    fun provideAlignmentRepository(): AlignmentsRepository {
        return alignmentsRepository
    }

    fun provideGendersRepository(): GendersRepository {
        return gendersRepository
    }

}