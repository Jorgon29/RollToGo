package com.terraplanistas.rolltogo.ui.screens.forumScreen.forumSearchScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForumCharacterSearchViewModel(
    private val charactersRepository: Any
): ViewModel() {
    private val _searchedItems = MutableStateFlow<List<DomainCharacter>>(emptyList())
    val searchedItems: StateFlow<List<DomainCharacter>> = _searchedItems

    fun searchByName(query: String) {
        /* viewModelScope.launch {
            charactersRepository.searchCharacter(query)
                .collect { results ->
                    _searchedItems.value = results
                }
        } */
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as RollToGoApp
                ForumCharacterSearchViewModel(
                    ""
                )
            }
        }
    }
}