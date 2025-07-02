package com.terraplanistas.rolltogo.data.repository.settings

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val datastore: DataStore<Preferences>
) {
    private companion object {
        val TEST_PREFERENCE = booleanPreferencesKey("test_preference")
        val LOGIN_PREFERENCE = booleanPreferencesKey("login_preference")
        val USERNAME_PREFERENCE = stringPreferencesKey("username_preference")
        val PASSWORD_PREFERENCE = stringPreferencesKey("password_preference")
        val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token_key")
    }

    suspend fun savePreference(testPreference: Boolean) {
        datastore.edit { preferences ->
            preferences[TEST_PREFERENCE] = testPreference
        }
    }

    // Guarda la preferencia de si se debe mantener la sesión iniciada
    suspend fun saveLoginPreference(loginPreference: Boolean) {
        datastore.edit { preference ->
            preference[LOGIN_PREFERENCE] = loginPreference

        }
    }
    // Guarda el nombre de usuario en las preferencias
    suspend fun saveUsernamePreference(username: String) {
        datastore.edit { preferences ->
            preferences[USERNAME_PREFERENCE] = username
        }
    }
    // Guarda la contraseña en las preferencias, encriptada
    suspend fun savePasswordPreference(password: String) {
        datastore.edit { preferences ->
            preferences[PASSWORD_PREFERENCE] = Encrypt.encrypt(password)
        }
    }

    suspend fun saveAuthTokenKeyPreference(token: String){
        datastore.edit { preferences ->
            preferences[AUTH_TOKEN_KEY] = token
        }
    }

    val authTokenPreference: Flow<String> = datastore.data
        .catch {
            if (it is IOException){
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[AUTH_TOKEN_KEY]?: ""
        }

    // Recupera la contraseña encriptada desde las preferencias y la desencripta antes de devolverla
    val passwordPreference: Flow<String> = datastore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[PASSWORD_PREFERENCE]?.let { Encrypt.decrypt(it) } ?: ""
        }
 // Recupera el nombre de usuario desde las preferencias
    val usernamePreference: Flow<String> = datastore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[USERNAME_PREFERENCE]?: ""
        }


    val testPreference: Flow<Boolean> = datastore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[TEST_PREFERENCE] ?: false
        }
    // Recupera la preferencia de si se debe mantener la sesión iniciada
    val loginPreference: Flow<Boolean> = datastore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[LOGIN_PREFERENCE] ?: false
        }


}