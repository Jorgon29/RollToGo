package com.terraplanistas.rolltogo.data.database.dao.rooms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomCreaturesEntity
import com.terraplanistas.rolltogo.data.enums.CreatureTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomCreaturesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoomCreature(roomCreature: RoomCreaturesEntity)

    @Update
    suspend fun updateRoomCreature(roomCreature: RoomCreaturesEntity)

    @Delete
    suspend fun deleteRoomCreature(roomCreature: RoomCreaturesEntity)

    @Query("SELECT * FROM room_creatures WHERE id = :roomCreatureId")
    fun getRoomCreatureById(roomCreatureId: String): Flow<RoomCreaturesEntity?>

    @Query("SELECT * FROM room_creatures WHERE room_id = :roomId")
    fun getRoomCreaturesByRoomId(roomId: String): Flow<List<RoomCreaturesEntity>>

    @Query("SELECT * FROM room_creatures WHERE owner_id = :ownerId")
    fun getRoomCreaturesByOwnerId(ownerId: String): Flow<List<RoomCreaturesEntity>>

    @Query("SELECT * FROM room_creatures WHERE creature_type = :creatureType")
    fun getRoomCreaturesByType(creatureType: CreatureTypeEnum): Flow<List<RoomCreaturesEntity>>

    @Query("SELECT * FROM room_creatures")
    fun getAllRoomCreatures(): Flow<List<RoomCreaturesEntity>>
}