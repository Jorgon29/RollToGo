package com.terraplanistas.rolltogo.ui.screens.content.screens.features

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentCreationState
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.featureStrategy.FeatureStrategy
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeatureCreationViewModel(
    private val repo: ContentCreationRepository
) : ViewModel() {

    private val currentStrategy: ContentStrategy = FeatureStrategy()
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _uiState = MutableStateFlow(ContentCreationState())
    val uiState: StateFlow<ContentCreationState> = _uiState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    private val _isValid = MutableStateFlow(false)
    val isValid: StateFlow<Boolean> = _isValid.asStateFlow()

    init {
        _uiState.value = ContentCreationState(
            formData = currentStrategy.getDefaultData(),
            isValid = currentStrategy.validateContent(currentStrategy.getDefaultData())
        )
    }
    fun continueWithDefaultData() {
        _uiState.value = ContentCreationState(
            formData = currentStrategy.getDefaultData(),
            isValid = currentStrategy.validateContent(currentStrategy.getDefaultData())
        )
        _isValid.value = false
    }

    fun updateField(key: String, value: Any) {
        val newData = _uiState.value.formData.toMutableMap().apply {
            put(key, value)
        }
        _uiState.value = _uiState.value.copy(
            formData = newData,
            isValid = currentStrategy.validateContent(newData)
        )
    }

    fun submitContent() {
        if (uiState.value.isValid) {
            _loading.value = true
            _errorMessage.value = null
            _isValid.value = false

            viewModelScope.launch {
                try {
                    currentStrategy.sumbit(uiState.value.formData, repo)
                    _isValid.value = true
                } catch (e: Exception) {
                    _errorMessage.value = "Error al crear el feature "
                    Log.e("FeatureCreation", "Error creating feature", e)
                } finally {

                    _loading.value = false

                }
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as RollToGoApp
                FeatureCreationViewModel(application.contentCreationRepository)
            }
        }
    }
}