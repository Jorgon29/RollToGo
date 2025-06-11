package com.terraplanistas.rolltogo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terraplanistas.rolltogo.data.database.entities.FriendEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao{
    @Query("SELECT * FROM friend")
    fun getFriends(): Flow<List<FriendEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFriend(friendEntity: FriendEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFriends(friends: List<FriendEntity>)

    @Delete
    suspend fun removeFriend(friendEntity: FriendEntity)
}