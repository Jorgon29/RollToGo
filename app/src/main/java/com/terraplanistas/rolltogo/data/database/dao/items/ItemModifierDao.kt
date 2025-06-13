package com.terraplanistas.rolltogo.data.database.dao.items

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.items.ItemModifier
import com.terraplanistas.rolltogo.data.enums.ItemModifierTypeEnum
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemModifierDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemModifier(itemModifier: ItemModifier)

    @Update
    suspend fun updateItemModifier(itemModifier: ItemModifier)

    @Delete
    suspend fun deleteItemModifier(itemModifier: ItemModifier)

    @Query("SELECT * FROM item_modifier WHERE id = :modifierId")
    fun getItemModifierById(modifierId: String): Flow<ItemModifier?>

    @Query("SELECT * FROM item_modifier WHERE item_id = :itemId")
    fun getItemModifiersByItemId(itemId: String): Flow<List<ItemModifier>>

    @Query("SELECT * FROM item_modifier WHERE modifier_type_enum = :modifierType")
    fun getItemModifiersByType(modifierType: ItemModifierTypeEnum): Flow<List<ItemModifier>>

    @Query("SELECT * FROM item_modifier")
    fun getAllItemModifiers(): Flow<List<ItemModifier>>
}