package com.terraplanistas.rolltogo.data.database.dao.classDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.classEntity.ClassEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(dndClass: ClassEntity)

    @Update
    suspend fun updateClass(dndClass: ClassEntity)

    @Delete
    suspend fun deleteClass(dndClass: ClassEntity)

    @Query("SELECT * FROM class WHERE id = :classId")
    fun getClassById(classId: String): Flow<ClassEntity?>

    @Query("SELECT * FROM class WHERE name LIKE :searchQuery || '%'")
    fun searchClassesByName(searchQuery: String): Flow<List<ClassEntity>>

    @Query("SELECT * FROM class")
    fun getAllClasses(): Flow<List<ClassEntity>>
}