package com.terraplanistas.rolltogo.data.database.dao.items

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.items.ItemEntity
import com.terraplanistas.rolltogo.data.enums.ItemRarityEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemEntity)

    @Update
    suspend fun updateItem(item: ItemEntity)

    @Delete
    suspend fun deleteItem(item: ItemEntity)

    @Query("SELECT * FROM items WHERE id = :itemId")
    fun getItemById(itemId: String): Flow<ItemEntity?>

    @Query("SELECT * FROM items WHERE name LIKE :searchQuery || '%'")
    fun searchItemsByName(searchQuery: String): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE item_type_enum = :itemType")
    fun getItemsByType(itemType: ItemTypeEnum): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE rarity_enum = :rarity")
    fun getItemsByRarity(rarity: ItemRarityEnum): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE attunement_required = :attunementRequired")
    fun getAttunementRequiredItems(attunementRequired: Boolean): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items WHERE it_magical = :isMagical")
    fun getMagicalItems(isMagical: Boolean): Flow<List<ItemEntity>>

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<ItemEntity>>
}