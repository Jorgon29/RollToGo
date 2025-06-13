package com.terraplanistas.rolltogo.data.database.dao.classDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.classEntity.SubclassEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubclassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubclass(subclass: SubclassEntity)

    @Update
    suspend fun updateSubclass(subclass: SubclassEntity)

    @Delete
    suspend fun deleteSubclass(subclass: SubclassEntity)

    @Query("SELECT * FROM subclass WHERE id = :subclassId")
    fun getSubclassById(subclassId: String): Flow<SubclassEntity?>

    @Query("SELECT * FROM subclass WHERE class_id = :classId")
    fun getSubclassesByClassId(classId: String): Flow<List<SubclassEntity>>

    @Query("SELECT * FROM subclass WHERE name LIKE :searchQuery || '%'")
    fun searchSubclassesByName(searchQuery: String): Flow<List<SubclassEntity>>

    @Query("SELECT * FROM subclass")
    fun getAllSubclasses(): Flow<List<SubclassEntity>>
}