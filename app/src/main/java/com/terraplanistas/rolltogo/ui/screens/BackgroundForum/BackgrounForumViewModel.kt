package com.terraplanistas.rolltogo.ui.screens.BackgroundForum

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class BackgroundForumViewModel(
    private val contentRepo: ContentCreationRepository
) : ViewModel() {
    private val _backgrounds = MutableStateFlow<List<BackgroundWithContent>>(emptyList())
    val backgrounds: StateFlow<List<BackgroundWithContent>> = _backgrounds.asStateFlow()

    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog.asStateFlow()

    private val _showEditDialog = MutableStateFlow(false)
    val showEditDialog: StateFlow<Boolean> = _showEditDialog.asStateFlow()

    private val _selectedBackground = MutableStateFlow<BackgroundWithContent?>(null)
    val selectedBackground: StateFlow<BackgroundWithContent?> = _selectedBackground.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadBackgrounds()
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun dismissEditDialog() {
        _showEditDialog.value = false
        _selectedBackground.value = null
    }

    fun loadBackgrounds() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val backgroundsContent = contentRepo.getContentsByType(SourceContentEnum.BACKGROUND)
                val backgroundsList = backgroundsContent.mapNotNull { content ->
                    contentRepo.getBackgroundByContentId(content.id)?.let { background ->
                        BackgroundWithContent(background, content)
                    }
                }
                _backgrounds.value = backgroundsList
            } catch (e: Exception) {
                Log.e("BackgroundForum", "Error loading backgrounds", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectBackgroundForEdit(background: BackgroundWithContent) {
        _selectedBackground.value = background
        _showDeleteDialog.value = false
        _showEditDialog.value = true
    }

    fun selectBackgroundForDelete(background: BackgroundWithContent) {
        _selectedBackground.value = background
        _showEditDialog.value = false
        _showDeleteDialog.value = true
    }

    fun deleteBackground() {
        viewModelScope.launch {
            try {
                selectedBackground.value?.let { background ->
                    contentRepo.deleteContent(background.content.id)
                    loadBackgrounds() // Recargar la lista después de borrar
                }
            } catch (e: Exception) {
                Log.e("BackgroundForum", "Error deleting background", e)
            } finally {
                _showDeleteDialog.value = false
            }
        }
    }

    fun updateBackground(updatedBackground: BackgroundWithContent) {
        viewModelScope.launch {
            try {
                contentRepo.updateBackground(
                    updatedBackground.background.copy(
                        id = updatedBackground.content.id
                    )
                )
                loadBackgrounds()
            } catch (e: Exception) {
                Log.e("BackgroundForum", "Error updating background", e)
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RollToGoApp
                BackgroundForumViewModel(app.contentCreationRepository)
            }
        }
    }
}