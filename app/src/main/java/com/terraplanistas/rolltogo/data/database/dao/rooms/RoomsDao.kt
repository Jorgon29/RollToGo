package com.terraplanistas.rolltogo.data.database.dao.rooms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.rooms.RoomsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(room: RoomsEntity)

    @Update
    suspend fun updateRoom(room: RoomsEntity)

    @Delete
    suspend fun deleteRoom(room: RoomsEntity)

    @Query("SELECT * FROM rooms WHERE id = :roomId")
    fun getRoomById(roomId: String): Flow<RoomsEntity?>

    @Query("SELECT * FROM rooms WHERE name LIKE :searchQuery || '%'")
    fun searchRoomsByName(searchQuery: String): Flow<List<RoomsEntity>>

    @Query("SELECT * FROM rooms")
    fun getAllRooms(): Flow<List<RoomsEntity>>
}