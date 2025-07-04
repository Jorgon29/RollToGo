package com.terraplanistas.rolltogo.data.repository.rooms

import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomParticipantEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomsEntity
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun getRoomsByPlayerId(playerId: String): Flow<List<RoomDomain?>>
    suspend fun createRoom(room: RoomsEntity)
    suspend fun createRoomParticipant(roomParticipant: RoomParticipantEntity)
    suspend fun addRoomParticipant(roomId: String, userId: String?)

}