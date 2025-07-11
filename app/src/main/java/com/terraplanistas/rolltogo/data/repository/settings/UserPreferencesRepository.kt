package com.terraplanistas.rolltogo.data.repository.settings

import android.util.Log
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
        val ROOM_ID = stringPreferencesKey("room_id")
        val ROOM_NAME = stringPreferencesKey("room_name")
        val USER_UUID = stringPreferencesKey("user_uuid")
    }

    suspend fun savePreference(testPreference: Boolean) {
        datastore.edit { preferences ->
            preferences[TEST_PREFERENCE] = testPreference
        }
    }

    suspend fun saveUserUuid(uuid: String){
        datastore.edit { preference ->
            preference[USER_UUID] = uuid
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

    val userUuid: Flow<String> = datastore.data
        .catch {
            if (it is IOException){
                Log.e("PreferencesRepo", "Error reading preferences: ${it.message}")
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[USER_UUID]?: "holaFromRepo"

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

    suspend fun saveRoomId(roomId: String) {
        datastore.edit { preferences ->
            preferences[ROOM_ID] = roomId
        }
    }

    val roomId: Flow<String> = datastore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[ROOM_ID] ?: ""
        }

    suspend fun saveRoomName(roomName: String) {
        datastore.edit { preferences ->
            preferences[ROOM_NAME] = roomName
        }
    }

    val roomName: Flow<String> = datastore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[ROOM_NAME] ?: ""
        }



}