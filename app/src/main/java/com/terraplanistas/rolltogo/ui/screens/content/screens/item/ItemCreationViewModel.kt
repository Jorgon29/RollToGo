package com.terraplanistas.rolltogo.ui.screens.content.screens.item

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentCreationState
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.ItemStrategy.ItemStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.backgroundStrategy.BackgroundStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.creatureStrategy.CreatureStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.spellStrategy.SpellStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ItemCreationViewModel(
    private val repo: ContentCreationRepository
) : ViewModel() {


    var currentStrategy: ContentStrategy = ItemStrategy()

    private val _uiState = MutableStateFlow(ContentCreationState())
    val uiState: StateFlow<ContentCreationState> = _uiState.asStateFlow()

    init {
        _uiState.value = ContentCreationState(
            formData = currentStrategy.getDefaultData(),
        )
    }

    fun updateFormData(data: Map<String, Any>) {
        _uiState.value = _uiState.value.copy(formData = data)
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication =
                    this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as? RollToGoApp
                        ?: throw IllegalStateException("Application is not RollToGoApp")
                ItemCreationViewModel(
                    repo = aplication.contentCreationRepository
                )

            }

        }

    }


}