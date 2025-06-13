package com.terraplanistas.rolltogo.data.database.dao.rooms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomParticipantEntity
import com.terraplanistas.rolltogo.data.enums.GameRoleEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomParticipantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomParticipant(roomParticipant: RoomParticipantEntity)

    @Update
    suspend fun updateRoomParticipant(roomParticipant: RoomParticipantEntity)

    @Delete
    suspend fun deleteRoomParticipant(roomParticipant: RoomParticipantEntity)

    @Query("SELECT * FROM room_participants WHERE id = :participantId AND room_id = :roomId")
    fun getRoomParticipantByIdAndRoomId(participantId: String, roomId: String): Flow<RoomParticipantEntity?>

    @Query("SELECT * FROM room_participants WHERE room_id = :roomId")
    fun getParticipantsByRoomId(roomId: String): Flow<List<RoomParticipantEntity>>

    @Query("SELECT * FROM room_participants WHERE id = :participantId")
    fun getRoomsByParticipantId(participantId: String): Flow<List<RoomParticipantEntity>>

    @Query("SELECT * FROM room_participants WHERE role_enum = :role")
    fun getParticipantsByRole(role: GameRoleEnum): Flow<List<RoomParticipantEntity>>

    @Query("SELECT * FROM room_participants")
    fun getAllRoomParticipants(): Flow<List<RoomParticipantEntity>>
}