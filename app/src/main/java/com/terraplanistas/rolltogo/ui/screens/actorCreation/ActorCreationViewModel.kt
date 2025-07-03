package com.terraplanistas.rolltogo.ui.screens.actorCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.model.CharacterAlignment
import com.terraplanistas.rolltogo.data.model.CharacterClass
import com.terraplanistas.rolltogo.data.model.CharacterGender
import com.terraplanistas.rolltogo.data.model.CharacterRace
import com.terraplanistas.rolltogo.data.model.Playstyle
import com.terraplanistas.rolltogo.data.repository.alignments.AlignmentsRepository
import com.terraplanistas.rolltogo.data.repository.characters.CharacterRepository
import com.terraplanistas.rolltogo.data.repository.classes.ClassesRepository
import com.terraplanistas.rolltogo.data.repository.genders.GendersRepository
import com.terraplanistas.rolltogo.data.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.repository.races.RaceRepository
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ActorCreationViewModel(
    private val playstyleRepository: PlaystyleRepository,
    private val classesRepository: ClassesRepository,
    private val racesRepository: RaceRepository,
    private val alignmentsRepository: AlignmentsRepository,
    private val gendersRepository: GendersRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val playstyleToClassMap = mapOf(
        1 to listOf(1, 5, 7),
        2 to listOf(10, 11, 12),
        3 to listOf(3, 2, 4),
        4 to listOf(8, 6, 4),
        5 to listOf(7, 2, 8)
    )

    private val classRaceAssociations = mapOf(
        1 to listOf(2, 3, 8),
        2 to listOf(4, 7, 2),
        3 to listOf(3, 1, 7),
        4 to listOf(2, 6, 4),
        5 to listOf(1, 3, 8),
        6 to listOf(8, 3, 1),
        7 to listOf(1, 3, 7),
        8 to listOf(4, 2, 1),
        9 to listOf(4, 9, 2),
        10 to listOf(9, 2, 1),
        11 to listOf(9, 2, 7),
        12 to listOf(6, 2, 1)
    )

    val userId: StateFlow<String> = preferencesRepository.userUuid.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = ""
    )


    fun getPlaystyles(): List<Playstyle> {
        return playstyleRepository.getPlaystyles()
    }

    fun getClasses(): List<CharacterClass> {
        return classesRepository.getClasses()
    }

    fun getAssociatedClasses(playstyleId: Int): List<CharacterClass> {
        val associatedIds = playstyleToClassMap[playstyleId] ?: emptyList()
        return getClasses().filter { it.id in associatedIds }
    }

    fun getUnassociatedClasses(playstyleId: Int): List<CharacterClass> {
        val associatedIds = playstyleToClassMap[playstyleId] ?: emptyList()
        return getClasses().filter { it.id !in associatedIds }
    }

    fun getAssociatedRaces(classId: Int): List<CharacterRace>{
        val associatedIds = classRaceAssociations[classId] ?: return emptyList()
        return racesRepository.getRaces().filter { it.id in associatedIds }
    }

    fun getUnassociatedRaces(classId: Int): List<CharacterRace>{
        val associatedIds = classRaceAssociations[classId] ?: return emptyList()
        return racesRepository.getRaces().filter { it.id !in associatedIds }
    }

    fun getAlignments(): List<CharacterAlignment>{
        return alignmentsRepository.getAlignments()
    }

    fun getGenders(): List<CharacterGender>{
        return gendersRepository.getGenders()
    }

    fun buildCharacter(character: ActorCreationContext){
       viewModelScope.launch {
           characterRepository.buildCharacter(character,userId.value)
       }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as? RollToGoApp
                ?: throw IllegalStateException("Application is not RollToGoApp")

                ActorCreationViewModel(
                    application.appProvider.providePlaystyleRepository(),
                    application.appProvider.provideClassesRepository(),
                    application.appProvider.provideRacesRepository(),
                    application.appProvider.provideAlignmentRepository(),
                    application.appProvider.provideGendersRepository(),
                    application.appProvider.provideUserPreferenceRepository(),
                    application.appProvider.provideCharactersRepository()
                )
            }
        }
    }
}