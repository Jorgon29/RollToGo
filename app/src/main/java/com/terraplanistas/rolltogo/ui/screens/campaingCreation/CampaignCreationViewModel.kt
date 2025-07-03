package com.terraplanistas.rolltogo.ui.screens.campaingCreation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomParticipantEntity
import com.terraplanistas.rolltogo.data.enums.RoleEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import com.terraplanistas.rolltogo.data.remote.RetrofitInstance
import com.terraplanistas.rolltogo.data.remote.dtos.ContentCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.RoomCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.RoomParticipantCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.toEntity
import com.terraplanistas.rolltogo.data.repository.rooms.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CampaignCreationViewModel(
    private val auth: FirebaseAuth,
    private val roomRepository: RoomRepository

    ) : ViewModel() {
    val _campaignState = MutableStateFlow<RoomDomain>(
        RoomDomain(
            id = "",
            name = "",
            ownerUserName = "",
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
        val userId = auth.currentUser?.uid ?: return
        val name = _campaignState.value.name
        val userName = auth.currentUser?.displayName ?: "Unknown User"
        val description = _campaignState.value.description

        viewModelScope.launch {
            val contentResponse = RetrofitInstance.contentService.createContent(
                ContentCreateRequest(
                    sourceContentEnum = SourceContentEnum.ROOMS,
                    visibilityEnum = VisibilityEnum.PRIVATE,
                    authorId = userId
                )
            ).body()

            val contentId = contentResponse?.id ?: return@launch

            val roomResponse = RetrofitInstance.roomService.createRoom(
                RoomCreateRequest(
                    contentId = contentId,
                    name = name,
                    description = description
                )
            ).body()

            val room = roomResponse?: return@launch

            val roomParticipantResponse = RetrofitInstance.roomParticipantService.createRoomParticipant(
                RoomParticipantCreateRequest(
                    roomId = room.id,
                    userId = userId,
                    roleEnum = RoleEnum.DUNGEON_MASTER
                )

            ).body()

            roomRepository.createRoom(room.toEntity())

            val roomParticipant = roomParticipantResponse?: return@launch

            Log.d("RoomParticipant", roomParticipant.toString())

            roomRepository.createRoomParticipant(
                RoomParticipantEntity(
                    id = roomParticipant.id,
                    user_id = roomParticipant.user.id,
                    room_id = room.id,
                    role_enum = roomParticipant.roleEnum
                )
            )


        }
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