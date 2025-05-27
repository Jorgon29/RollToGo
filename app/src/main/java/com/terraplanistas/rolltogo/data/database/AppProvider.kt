package com.terraplanistas.rolltogo.data.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.terraplanistas.rolltogo.data.database.repository.settings.UserPreferencesRepository
import com.terraplanistas.rolltogo.data.database.dao.PlaystyleDao
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepositoryImplementation

private val USER_PREFERENCE_NAME = "user_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE_NAME)

class AppProvider (context: Context){
    private val appDatabase: RollToGoDatabase = RollToGoDatabase.getDatabase(context)

    private val playstyleDao: PlaystyleDao = appDatabase.playstyleDao()

    private val playstyleRepository: PlaystyleRepository = PlaystyleRepositoryImplementation(playstyleDao, context)
    private val userPreferenceRepository: UserPreferencesRepository = UserPreferencesRepository(context.dataStore)

    fun providePlaystyleRepository(): PlaystyleRepository {
        return playstyleRepository
    }

    fun provideUserPreferenceRepository(): UserPreferencesRepository {
        return userPreferenceRepository
    }
}