package com.terraplanistas.rolltogo.ui.screens.campaingCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import com.terraplanistas.rolltogo.data.repository.rooms.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
class CampaignCreationViewModel(
    private val auth: FirebaseAuth,
    private val roomRepository: RoomRepository,

    ) : ViewModel() {
    val _campaignState = MutableStateFlow<RoomDomain>(
        RoomDomain(
            id = "",
            name = "",
            description = ""
        )
    )
    val campaignState = _campaignState.asStateFlow()
    fun updateName(name: String) {
        _campaignState.value = _campaignState.value.copy(name = name)
    }
    fun updateDescription(description: String) {
        _campaignState.value = _campaignState.value.copy(description = description)
    }
    fun createCampaign() {
        TODO("Esperando retrofit")
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RollToGoApp
                CampaignCreationViewModel(
                    auth = FirebaseAuth.getInstance(),
                    roomRepository = app.roomsRepository
                )
            }
        }
    }


}