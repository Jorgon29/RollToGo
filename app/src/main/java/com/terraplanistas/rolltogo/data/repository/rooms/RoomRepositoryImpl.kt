package com.terraplanistas.rolltogo.data.repository.rooms

import com.terraplanistas.rolltogo.data.database.dao.UserDao
import com.terraplanistas.rolltogo.data.database.dao.UserDao_Impl
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomParticipantDao
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomParticipantDao_Impl
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomsDao
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomsDao_Impl
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomParticipantEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomsEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.toDomain
import com.terraplanistas.rolltogo.data.enums.RoleEnum
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class RoomRepositoryImpl(
    val userDao: UserDao,
    val roomsDao: RoomsDao,
    val roomParticipantDao: RoomParticipantDao
) : RoomRepository {

    override fun getRoomsByPlayerId(playerId: String): Flow<List<RoomDomain?>> {
        return roomParticipantDao.getRoomsByParticipantId(playerId).map { entity ->
            entity.mapNotNull {
                roomsDao.getRoomById(it.room_id).firstOrNull()?.toDomain()
            }
        }
    }

    override suspend fun createRoom(room: RoomsEntity) {
        roomsDao.insertRoom(room)
    }

    override suspend fun createRoomParticipant(roomParticipant: RoomParticipantEntity) {
        roomParticipantDao.insertRoomParticipant(roomParticipant)
    }
}