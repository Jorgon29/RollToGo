package com.terraplanistas.rolltogo.ui.screens.profile

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import android.net.Uri
import android.util.Log

import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserProfileChangeRequest
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfielScreenViewmodel(
    private val auth: FirebaseAuth,
    private val persistUri: (Uri) -> Uri?,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _username = MutableStateFlow(auth.currentUser?.displayName)
    val username: StateFlow<String?> = _username.asStateFlow()

    private val _userPicture = MutableStateFlow(auth.currentUser?.photoUrl)
    val userPicture: StateFlow<Uri?> = _userPicture.asStateFlow()

    private val _userEmail = MutableStateFlow(auth.currentUser?.email)
    val userEmail: StateFlow<String?> = _userEmail.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _loading.value = true
                auth.currentUser?.reload()?.await()
                _username.value = auth.currentUser?.displayName
                _userPicture.value = auth.currentUser?.photoUrl
                _userEmail.value = auth.currentUser?.email
                Log.d("userid", "User ID: ${auth.currentUser?.uid}, token: ${auth.currentUser?.getIdToken(false)?.await()?.token}")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun updateDisplayName(newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.emit(true)
            try {
                val request = UserProfileChangeRequest.Builder()
                    .setDisplayName(newName)
                    .setPhotoUri(auth.currentUser?.photoUrl)
                    .build()

                auth.currentUser?.updateProfile(request)?.await()
                _username.emit(newName)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.emit(false)
            }
        }
    }

    fun onImagePicked(newUri: Uri) {
        viewModelScope.launch {
            _loading.value = true
            persistUri(newUri).let{
                val update = UserProfileChangeRequest.Builder()
                    .setPhotoUri(it)
                    .setDisplayName(auth.currentUser?.displayName)
                    .build()
                auth.currentUser?.updateProfile(update)?.await()
                _userPicture.value = newUri
            }

            _loading.value = false
        }
    }

    fun logout() {
        viewModelScope.launch {
            _loading.value = true
            try {
                auth.signOut()
                preferencesRepository.saveLoginPreference(loginPreference = false)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as? RollToGoApp
                    ?: error("App is not RollToGoApp")
                ProfielScreenViewmodel(
                    app.fireBaseAuth,
                    persistUri = app.getSafeUriForFirebase,
                    preferencesRepository = app.appProvider.provideUserPreferenceRepository()
                )
            }
        }
    }
}


