package com.terraplanistas.rolltogo.ui.screens.campaignList

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch


class CampaignListViewModel(
 private val campaignListRepository: RoomRepository
): ViewModel() {


    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid

    private val _campaigns = MutableStateFlow<Resource<List<RoomDomain>>>(Resource.Loading)
    val campaigns: StateFlow<Resource<List<RoomDomain>>> = _campaigns.asStateFlow()
    private val _showJoin = MutableStateFlow(false)
    val showJoin = _showJoin.asStateFlow()

    private val _joinCode = MutableStateFlow("")
    val joinCode = _joinCode.asStateFlow()

    private val _showJoinDialog = MutableStateFlow(false)
    val showJoinDialog = _showJoinDialog.asStateFlow()

    fun updateJoinCode(code: String) {
        _joinCode.value = code
    }

    fun showJoinDialog(show: Boolean) {
        _showJoinDialog.value = show
    }

    fun joinCampaign() {
        viewModelScope.launch {
            try {

                campaignListRepository.addRoomParticipant(roomId =  _joinCode.value, userId = uid )
                loadMyCampaigns()

                switchIsShowing(true)
                showJoinDialog(false)
                _joinCode.value = ""
            } catch (e: Exception) {
            }
            loadMyCampaigns()

        }
    }


    fun loadMyCampaigns() {
        viewModelScope.launch {
            _campaigns.value = Resource.Loading
            try {
                campaignListRepository.getRoomsByPlayerId(uid.toString()).collect { roomList ->
                    val nonNullRooms = roomList.filterNotNull()
                    _campaigns.value = Resource.Success(nonNullRooms)
                }
            } catch (e: Exception) {
                _campaigns.value = Resource.Error("Error: ${e.localizedMessage}")
            }
        }
    }

    fun switchIsShowing(value: Boolean){
        _showJoin.value = value
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