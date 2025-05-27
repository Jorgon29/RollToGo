package com.terraplanistas.rolltogo.ui.screens.actorCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.database.repository.playstyleRepository.PlaystyleRepository
import com.terraplanistas.rolltogo.data.model.Playstyle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ActorCreationViewModel(
    private val playstyleRepository: PlaystyleRepository
) : ViewModel() {

    fun getPlaystyles(): List<Playstyle> {
        return playstyleRepository.getPlaystyles()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as? RollToGoApp
                ?: throw IllegalStateException("Application is not RollToGoApp")

                ActorCreationViewModel(application.appProvider.providePlaystyleRepository())
            }
        }
    }
}