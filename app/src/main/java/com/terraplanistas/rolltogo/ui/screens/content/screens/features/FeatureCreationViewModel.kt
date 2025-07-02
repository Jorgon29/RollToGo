package com.terraplanistas.rolltogo.ui.screens.content.screens.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentCreationState
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.featureStrategy.FeatureStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FeatureCreationViewModel(
    private val repo: ContentCreationRepository
) : ViewModel() {

    private val currentStrategy: ContentStrategy = FeatureStrategy()

    private val _uiState = MutableStateFlow(ContentCreationState())
    val uiState: StateFlow<ContentCreationState> = _uiState.asStateFlow()

    init {
        _uiState.value = ContentCreationState(
            formData = currentStrategy.getDefaultData(),
            isValid = currentStrategy.validateContent(currentStrategy.getDefaultData())
        )
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
            currentStrategy.sumbit(uiState.value.formData, repo)
        }
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