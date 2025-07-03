package com.terraplanistas.rolltogo.ui.screens.content.screens.background

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.enums.ProficiencyLevelEnum
import com.terraplanistas.rolltogo.data.enums.ProficiencyTypeEnum
import com.terraplanistas.rolltogo.data.repository.contentCreation.ContentCreationRepository
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.dataHolders.FeatureUI
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.dataHolders.ProficiencyUI
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentCreationState
import com.terraplanistas.rolltogo.ui.screens.content.strategy.ContentStrategy
import com.terraplanistas.rolltogo.ui.screens.content.strategy.concreteStrategies.backgroundStrategy.BackgroundStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class BackgroundCreationViewModel(
    private val repo: ContentCreationRepository
) : ViewModel() {

    private val _strategy: ContentStrategy = BackgroundStrategy()

    fun getStrategy(): ContentStrategy {
        return _strategy
    }
    private val _uiState = MutableStateFlow(ContentCreationState())
    val uiState: StateFlow<ContentCreationState> = _uiState.asStateFlow()

    private val _availableProficiencies = MutableStateFlow<List<ProficiencyUI>>(emptyList())
    val availableProficiencies: StateFlow<List<ProficiencyUI>> = _availableProficiencies.asStateFlow()

    private val _availableFeatures = MutableStateFlow<List<FeatureUI>>(emptyList())
    val availableFeatures: StateFlow<List<FeatureUI>> = _availableFeatures.asStateFlow()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    private val _isValid = MutableStateFlow(false)
    val isValid: StateFlow<Boolean> = _isValid.asStateFlow()
    private val _isLoadin = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoadin.asStateFlow()

    init {
        resetToDefault()
        loadInitialData()
    }

    private fun resetToDefault() {
        _uiState.value = ContentCreationState(
            formData = _strategy.getDefaultData()
        )
    }

    fun continueWithDefaultData() {
        _uiState.value = ContentCreationState(
            formData = _strategy.getDefaultData()
        )
        _errorMessage.value = null
        _isValid.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _availableProficiencies.value = listOf(
                ProficiencyUI("Deception", "charisma", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Intimidation", "charisma", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Performance", "charisma", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Persuasion", "charisma", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Animal Handling", "wisdom", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Insight", "wisdom", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Medicine", "wisdom", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Perception", "wisdom", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Survival", "wisdom", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Arcana", "intelligence", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("History", "intelligence", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Investigation", "intelligence", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Nature", "intelligence", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Religion", "intelligence", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Acrobatics", "dexterity", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Sleight of Hand", "dexterity", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Stealth", "dexterity", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Athletics", "strength", ProficiencyTypeEnum.SKILL),

                // Tools
                ProficiencyUI("Alchemist's supplies", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Brewer's supplies", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Calligrapher's supplies", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Carpenter's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Cartographer's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Cobbler's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Cook's utensils", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Glassblower's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Jeweler's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Leatherworker's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Mason's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Painter's supplies", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Potter's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Smith's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Tinker's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Weaver's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Woodcarver's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Dice set", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Dragonchess set", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Playing card set", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Three-Dragon Ante set", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Bagpipes", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Drum", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Dulcimer", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Flute", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Lute", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Lyre", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Horn", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Pan flute", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Shawm", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Viol", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Disguise kit", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Forgery kit", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Herbalism kit", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Navigator's tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Poisoner's kit", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Thieves' tools", "intelligence", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Vehicles (land or water)", "intelligence", ProficiencyTypeEnum.TOOL),

                // Weapons
                ProficiencyUI("Battleaxe", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Flail", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Glaive", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Greataxe", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Greatsword", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Halberd", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Lance", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Longsword", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Maul", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Morningstar", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Pike", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Rapier", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Scimitar", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Shortsword", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Trident", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("War pick", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Warhammer", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Whip", "strength", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Blowgun", "dexterity", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Crossbow, hand", "dexterity", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Crossbow, heavy", "dexterity", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Longbow", "dexterity", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Net", "dexterity", ProficiencyTypeEnum.WEAPON),

                // Saving Throws
                ProficiencyUI("Strength", "strength", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Dexterity", "dexterity", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Constitution", "constitution", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Intelligence", "intelligence", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Wisdom", "wisdom", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Charisma", "charisma", ProficiencyTypeEnum.SAVING_THROW)
            )

            // Cargar features disponibles
            _availableFeatures.value = repo.getFeatures().map { feature ->
                FeatureUI(feature.id, feature.name, feature.description)
            }
        }
    }

    // Actualizaciones del formulario
    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(
            formData = _uiState.value.formData + ("name" to name)
        )
    }

    fun updateDescription(description: String) {
        _uiState.value = _uiState.value.copy(
            formData = _uiState.value.formData + ("description" to description)
        )
    }

    // Manejo de selecciones
    fun toggleProficiency(proficiency: ProficiencyUI, level: ProficiencyLevelEnum) {
        val current = (_uiState.value.formData["proficiencies"] as? List<Map<String, String>>)?.toMutableList() ?: mutableListOf()

        val newProficiency = mapOf(
            "name" to proficiency.name,
            "type" to level.name,
            "ability" to proficiency.ability,
            "proficiency_type" to proficiency.type.name
        )

        current.removeAll { it["name"] == proficiency.name }
        if (level != ProficiencyLevelEnum.NOT_PROFICIENT) {
            current.add(newProficiency)
        }

        _uiState.value = _uiState.value.copy(
            formData = _uiState.value.formData + ("proficiencies" to current)
        )
    }

    fun toggleFeature(feature: FeatureUI) {
        val current = (_uiState.value.formData["feature_ids"] as? List<String>)?.toMutableList() ?: mutableListOf()

        if (current.contains(feature.id)) {
            current.remove(feature.id)
        } else {
            current.add(feature.id)
        }

        _uiState.value = _uiState.value.copy(
            formData = _uiState.value.formData + ("feature_ids" to current)
        )
    }

    // Envío de datos
     fun submit() {
        if (_strategy.validateContent(_uiState.value.formData)) {
            _isLoadin.value = true
            _errorMessage.value = null
            _isValid.value = false

            viewModelScope.launch {
                try {
                    Log.d("","${uiState.value.formData}")
                    _strategy.sumbit(uiState.value.formData, repo)
                    _isValid.value = true

                } catch (e: Exception) {
                    _errorMessage.value = "Error al crear el feature "
                    Log.e("FeatureCreation", "Error creating feature", e)
                } finally {

                    _isLoadin.value = false

                }
            }
        }


    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RollToGoApp
                BackgroundCreationViewModel(app.contentCreationRepository)
            }
        }
    }
}