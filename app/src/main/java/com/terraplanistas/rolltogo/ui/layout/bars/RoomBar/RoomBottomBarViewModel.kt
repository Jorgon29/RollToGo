package com.terraplanistas.rolltogo.ui.layout.bars.RoomBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository

class RoomBottomBarViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    @Composable
    fun getCurrentRoomId(): String {
        return userPreferencesRepository.roomId.collectAsState(initial = "").value

    }

    @Composable
    fun getCurrentRoomTitle(): String {
        return userPreferencesRepository.roomName.collectAsState(initial = "").value
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                RoomBottomBarViewModel(
                    userPreferencesRepository = aplication.appProvider.provideUserPreferenceRepository()
                )

            }

        }
    }

}