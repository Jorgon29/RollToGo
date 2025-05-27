package com.terraplanistas.rolltogo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terraplanistas.rolltogo.data.database.entities.PlaystyleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaystyleDao {
    @Query("SELECT * FROM PLAYSTYLE")
    fun getPlaystyles(): Flow<List<PlaystyleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlaystyle(playstyleEntity: PlaystyleEntity)
}