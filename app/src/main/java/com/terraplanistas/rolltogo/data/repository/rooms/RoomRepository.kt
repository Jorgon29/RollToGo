package com.terraplanistas.rolltogo.data.repository.rooms

import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomsEntity
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun getRoomsByPlayerId(playerId: String): Flow<List<RoomDomain?>>
    fun createRoom(room: RoomsEntity)

}