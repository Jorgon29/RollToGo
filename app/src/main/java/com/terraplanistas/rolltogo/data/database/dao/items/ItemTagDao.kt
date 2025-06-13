package com.terraplanistas.rolltogo.data.database.dao.items

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.items.ItemTagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemTagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemTag(itemTag: ItemTagEntity)

    @Delete
    suspend fun deleteItemTag(itemTag: ItemTagEntity)

    @Query("SELECT * FROM item_tags WHERE items_id = :itemId")
    fun getTagsByItemId(itemId: String): Flow<List<ItemTagEntity>>

    @Query("SELECT * FROM item_tags WHERE tag = :tag")
    fun getItemsByTag(tag: String): Flow<List<ItemTagEntity>>

    @Query("SELECT * FROM item_tags WHERE items_id = :itemId AND tag = :tag")
    fun getItemTag(itemId: String, tag: String): Flow<ItemTagEntity?>
}