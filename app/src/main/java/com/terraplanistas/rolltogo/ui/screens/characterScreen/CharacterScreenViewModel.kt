package com.terraplanistas.rolltogo.ui.screens.characterScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.defaults.getDummyCharacter
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.data.repository.characters.CharacterRepository
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterScreenViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {
    private val _characterResource = MutableStateFlow<Resource<DomainCharacter>>(Resource.Loading)
    val characterResource: StateFlow<Resource<DomainCharacter>> = _characterResource.asStateFlow()
    fun loadCharacter(id: String) {
        viewModelScope.launch {
            characterRepository.getCharacterById(id)
                .collect { resource ->
                    _characterResource.value = resource
                    if (resource is Resource.Error) {
                        println("Error loading character: ${resource.message}")
                    } else if (resource is Resource.Loading) {
                        println("Loading character...")
                    }
                }
        }
    }

    fun loadDummyCharacter() {
        viewModelScope.launch {
            _characterResource.value = Resource.Loading
            delay(1000)

            val dummyCharacter = getDummyCharacter()
            _characterResource.value = Resource.Success(dummyCharacter)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                CharacterScreenViewModel(
                    application.charactersRepository
                )
            }
        }
    }
}
