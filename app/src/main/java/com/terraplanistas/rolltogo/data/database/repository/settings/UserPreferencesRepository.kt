package com.terraplanistas.rolltogo.data.database.repository.settings

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository (
    private val datastore: DataStore<Preferences>
) {
    private companion object {
        val TEST_PREFERENCE = booleanPreferencesKey("test_preference")
    }

    suspend fun savePreference(testPreference: Boolean){
        datastore.edit { preferences ->
            preferences[TEST_PREFERENCE] = testPreference
        }
    }

    val testPreference: Flow<Boolean> = datastore.data
        .catch {
            if(it is IOException){
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[TEST_PREFERENCE] ?: false
    }
}