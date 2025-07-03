package com.terraplanistas.rolltogo.ui.screens.campaignList

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import com.terraplanistas.rolltogo.data.repository.rooms.RoomRepository
import com.terraplanistas.rolltogo.helpers.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CampaignListViewModel(
 private val campaignListRepository: RoomRepository,

): ViewModel() {


    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid

    private val _campaigns = MutableStateFlow<Resource<List<RoomDomain>>>(Resource.Loading)
    val campaigns: StateFlow<Resource<List<RoomDomain>>> = _campaigns.asStateFlow()

    suspend fun loadMyCampaigns() {
        campaignListRepository.getRoomsByPlayerId(uid.toString()).collect { resource ->
            when (resource) {
                is Resource.Success<*> -> {
                    val data = resource.data as? List<RoomDomain?>
                    _campaigns.value = Resource.Success(data?.filterNotNull() ?: emptyList())
                }
                is Resource.Error -> _campaigns.value = Resource.Error(resource.message)
                is Resource.Loading -> _campaigns.value = Resource.Loading
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as? RollToGoApp
                    ?: throw IllegalStateException("Application is not RollToGoApp")
                CampaignListViewModel(
                    campaignListRepository = aplication.roomsRepository
                )

            }

        }
    }

}