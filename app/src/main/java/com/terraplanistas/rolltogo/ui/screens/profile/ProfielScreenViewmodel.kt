package com.terraplanistas.rolltogo.ui.screens.profile

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfielScreenViewmodel(
    private val auth: FirebaseAuth,
     val persistUri: (Uri) -> Uri?
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
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }
    }

    fun getPersistUri(uri: Uri): Uri? {
        return try {
            persistUri(uri)
        } catch (e: Exception) {
            e.printStackTrace()
            null
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

    fun onImagePicked(newPic: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.emit(true)
            try {
                val request = UserProfileChangeRequest.Builder()
                    .setPhotoUri(newPic)
                    .setDisplayName(auth.currentUser?.displayName)
                    .build()

                auth.currentUser?.updateProfile(request)?.await()
                _userPicture.emit(newPic)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.emit(false)
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
                    persistUri = app.getSafeUriForFirebase
                )
            }
        }
    }
}


