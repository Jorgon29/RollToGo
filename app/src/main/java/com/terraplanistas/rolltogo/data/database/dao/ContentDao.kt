package com.terraplanistas.rolltogo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.ContentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContent(content: ContentEntity)

    @Update
    suspend fun updateContent(content: ContentEntity)

    @Delete
    suspend fun deleteContent(content: ContentEntity)

    @Query("SELECT * FROM content WHERE id = :contentId")
    fun getContentById(contentId: String): Flow<ContentEntity?>

    @Query("SELECT * FROM content WHERE author_id = :authorId")
    fun getContentByAuthorId(authorId: String): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content WHERE source_content_enum = :sourceType")
    fun getContentBySourceType(sourceType: String): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content WHERE visibility_enum = :visibility")
    fun getContentByVisibility(visibility: String): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content")
    fun getAllContent(): Flow<List<ContentEntity>>
}