package com.terraplanistas.rolltogo.data.repository.rooms

import android.util.Log
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
import com.terraplanistas.rolltogo.data.remote.RetrofitInstance
import com.terraplanistas.rolltogo.data.remote.dtos.RoomParticipantCreateRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.UUID

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

    override suspend fun addRoomParticipant(roomId: String, userId: String?) {

        val contentRoom = RetrofitInstance.contentService
            .getContentById(UUID.fromString(roomId))

        val room = RetrofitInstance.roomService
            .getRoomById(UUID.fromString(roomId))

        val authorID = contentRoom.author.username


        val roomENtity = RoomsEntity(
            id = room.id,
            name = room.name,
            ownerUserName = authorID,
            description = room.description ?: ""
        )

        Log.d("entity", "$roomENtity")

        roomsDao.insertRoom(
            roomENtity

        )
        Log.d("coño", "AA")

        val creatRoomParticipant = RetrofitInstance.roomParticipantService
            .createRoomParticipant(
                RoomParticipantCreateRequest(
                    roomId = room.id,
                    userId = userId ?: "",
                    roleEnum = RoleEnum.PLAYER
                )
            ).body()

        creatRoomParticipant?.let {  roomparticipant ->

            val createRoomENtity = RoomParticipantEntity(
                id = roomparticipant.id,
                user_id = userId ?: "",
                room_id = roomId  ,
                role_enum = roomparticipant.roleEnum
            )
            roomParticipantDao.insertRoomParticipant(createRoomENtity)

        }
    }
}