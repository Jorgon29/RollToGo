package com.terraplanistas.rolltogo.data.repository.rooms

import com.terraplanistas.rolltogo.data.database.dao.UserDao
import com.terraplanistas.rolltogo.data.database.dao.UserDao_Impl
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomParticipantDao
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomParticipantDao_Impl
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomsDao
import com.terraplanistas.rolltogo.data.database.dao.rooms.RoomsDao_Impl
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomsEntity
import com.terraplanistas.rolltogo.data.database.entities.rooms.toDomain
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class RoomRepositoryImpl(
    val userDao: UserDao,
    val roomsDao: RoomsDao,
    val roomParticipantDao: RoomParticipantDao
) : RoomRepository {

    override suspend fun getRoomsByPlayerId(playerId: String): Flow<List<RoomDomain>> {
        return roomParticipantDao.getRoomsByParticipantId(playerId).map { entity ->
            entity.mapNotNull {
                roomsDao.getRoomById(it.room_id).firstOrNull()?.toDomain()
            }
        }
    }

    override fun createRoom(room: RoomsEntity) {


    }
}