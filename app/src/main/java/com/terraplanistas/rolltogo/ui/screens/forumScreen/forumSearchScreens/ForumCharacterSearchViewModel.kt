package com.terraplanistas.rolltogo.ui.screens.forumScreen.forumSearchScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.database.repository.BaseRepository
import com.terraplanistas.rolltogo.data.database.repository.character.CharactersRepository
import com.terraplanistas.rolltogo.data.model.DomainCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ForumCharacterSearchViewModel(
    private val charactersRepository: CharactersRepository
): ViewModel() {
    private val _searchedItems = MutableStateFlow<List<DomainCharacter>>(emptyList())
    val searchedItems: StateFlow<List<DomainCharacter>> = _searchedItems

    fun searchByName(query: String) {
        viewModelScope.launch {
            charactersRepository.searchCharacter(query)
                .collect { results ->
                    _searchedItems.value = results
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as RollToGoApp
                ForumCharacterSearchViewModel(
                    application.charactersRepository
                )
            }
        }
    }
}