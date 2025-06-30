package com.terraplanistas.rolltogo.ui.screens.content.screens.background

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
import com.terraplanistas.rolltogo.ui.screens.content.contentcreation.ContentCreationScreen
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

    init {
        resetToDefault()
        loadInitialData()
    }

    private fun resetToDefault() {
        _uiState.value = ContentCreationState(
            formData = _strategy.getDefaultData()
        )
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _availableProficiencies.value = listOf(
                ProficiencyUI("Deception", "cha", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Intimidation", "cha", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Performance", "cha", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Persuasion", "cha", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Animal Handling", "wis", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Insight", "wis", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Medicine", "wis", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Perception", "wis", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Survival", "wis", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Arcana", "int", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("History", "int", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Investigation", "int", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Nature", "int", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Religion", "int", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Acrobatics", "dex", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Sleight of Hand", "dex", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Stealth", "dex", ProficiencyTypeEnum.SKILL),
                ProficiencyUI("Athletics", "str", ProficiencyTypeEnum.SKILL),

                // Tools
                ProficiencyUI("Alchemist's supplies", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Brewer's supplies", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Calligrapher's supplies", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Carpenter's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Cartographer's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Cobbler's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Cook's utensils", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Glassblower's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Jeweler's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Leatherworker's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Mason's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Painter's supplies", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Potter's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Smith's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Tinker's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Weaver's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Woodcarver's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Dice set", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Dragonchess set", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Playing card set", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Three-Dragon Ante set", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Bagpipes", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Drum", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Dulcimer", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Flute", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Lute", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Lyre", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Horn", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Pan flute", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Shawm", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Viol", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Disguise kit", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Forgery kit", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Herbalism kit", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Navigator's tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Poisoner's kit", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Thieves' tools", "int", ProficiencyTypeEnum.TOOL),
                ProficiencyUI("Vehicles (land or water)", "int", ProficiencyTypeEnum.TOOL),

                // Weapons
                ProficiencyUI("Battleaxe", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Flail", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Glaive", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Greataxe", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Greatsword", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Halberd", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Lance", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Longsword", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Maul", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Morningstar", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Pike", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Rapier", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Scimitar", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Shortsword", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Trident", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("War pick", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Warhammer", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Whip", "str", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Blowgun", "dex", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Crossbow, hand", "dex", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Crossbow, heavy", "dex", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Longbow", "dex", ProficiencyTypeEnum.WEAPON),
                ProficiencyUI("Net", "dex", ProficiencyTypeEnum.WEAPON),

                // Saving Throws
                ProficiencyUI("Strength", "str", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Dexterity", "dex", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Constitution", "con", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Intelligence", "int", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Wisdom", "wis", ProficiencyTypeEnum.SAVING_THROW),
                ProficiencyUI("Charisma", "cha", ProficiencyTypeEnum.SAVING_THROW)
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
    suspend fun submit() {
        _strategy.sumbit(_uiState.value.formData, repo)
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