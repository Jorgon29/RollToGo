package com.terraplanistas.rolltogo.ui.screens.featuresForum

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

class FeatureForumScreenViewModel(
    private val contentRepo: ContentCreationRepository
) : ViewModel() {
    private val _features = MutableStateFlow<List<FeatureWithContent>>(emptyList())
    val features: StateFlow<List<FeatureWithContent>> = _features.asStateFlow()

    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog.asStateFlow()
    private val _showEditDialog =MutableStateFlow(false)
    val showEditDialog: StateFlow<Boolean> = _showEditDialog.asStateFlow()

    private val _selectedFeature = MutableStateFlow<FeatureWithContent?>(null)
    val selectedFeature: StateFlow<FeatureWithContent?> = _selectedFeature.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadFeatures()
    }

    fun dismissDeleteDialog() {
        _showDeleteDialog.value = false
    }

    fun dismissEditDialog() {
        _selectedFeature.value = null
    }

    fun loadFeatures() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val featuresContent = contentRepo.getContentsByType(SourceContentEnum.FEATURES)
                val featuresList = featuresContent.mapNotNull { content ->
                    contentRepo.getFeatureByContentId(content.id)?.let { feature ->
                        FeatureWithContent(feature, content)
                    }
                }
                _features.value = featuresList
            } catch (e: Exception) {
                Log.e("FeatureForum", "Error loading features", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectFeatureForEdit(feature: FeatureWithContent) {
        _selectedFeature.value = feature
        _showDeleteDialog.value = false
        _showEditDialog.value = true
    }

    fun selectFeatureForDelete(feature: FeatureWithContent) {
        _selectedFeature.value = feature
        _showEditDialog.value = false
        _showDeleteDialog.value = true
    }

    fun deleteFeature() {
        viewModelScope.launch {
            try {
                selectedFeature.value?.let { feature ->
                    contentRepo.deleteContent(feature.content.id)
                    loadFeatures() // Recargar la lista después de borrar
                }
            } catch (e: Exception) {
                Log.e("FeatureForum", "Error deleting feature", e)
            } finally {
                _showDeleteDialog.value = false
            }
        }
    }

    fun updateFeature(updatedFeature: FeatureWithContent) {
        viewModelScope.launch {
            try {
                // Actualizar el contenido primero
                //contentRepo.updateContent(updatedFeature.content)

                // Luego actualizar el feature
                contentRepo.updateFeature(
                    updatedFeature.feature.copy(
                        id = updatedFeature.content.id
                    )
                )

                loadFeatures() // Recargar la lista después de actualizar
            } catch (e: Exception) {
                Log.e("FeatureForum", "Error updating feature", e)
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RollToGoApp
                FeatureForumScreenViewModel(app.contentCreationRepository)
            }
        }
    }
}