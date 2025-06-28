package com.terraplanistas.rolltogo.ui.screens.content

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentCreationState
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.ItemStrategy.WeaponStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.backgroundStrategy.BackgroundStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.creatureStrategy.CreatureStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.spellStrategy.SpellStrategy
import com.terraplanistas.rolltogo.ui.screens.login.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContentCreationViewModel(
    private val repo: ContentCreationRepository
) : ViewModel() {

    fun getRepository(): ContentCreationRepository {
        return repo
    }

    private var _currentContentType: SourceContentEnum? = null
    var currentStrategy: ContentStrategy? = null

    private val _uiState = MutableStateFlow(ContentCreationState())
    val uiState: StateFlow<ContentCreationState> = _uiState.asStateFlow()

    fun setContentType(type: SourceContentEnum) {
        Log.d("Cosa", "Setting content type to: ${type.value}")
        _currentContentType = type
        currentStrategy = when (type) {
            SourceContentEnum.ITEM -> WeaponStrategy()
            SourceContentEnum.SPELLS -> SpellStrategy()
            SourceContentEnum.BACKGROUND -> BackgroundStrategy()
            SourceContentEnum.CREATURES -> CreatureStrategy()
            SourceContentEnum.SPECIES -> CreatureStrategy()
            else -> throw IllegalArgumentException("Unsupported content type: $type")
        }
        Log.d("AAA","Se asigno el tipo de estrategia")
        _uiState.value = _uiState.value.copy(
            formData = currentStrategy?.getDefaultData() ?: emptyMap()
        )
        Log.d("Contenido esperado", "${_uiState.value.formData["is_magical"]} ${_uiState.value.formData["attunment"]}")
    }

    fun updateFormData(data: Map<String, Any>) {
        _uiState.value = _uiState.value.copy(formData = data)
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                ContentCreationViewModel(
                    repo = aplication.contentCreationRepository
                )

            }

        }

    }


}