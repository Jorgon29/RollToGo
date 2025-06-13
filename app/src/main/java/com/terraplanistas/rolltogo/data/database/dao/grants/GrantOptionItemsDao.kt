package com.terraplanistas.rolltogo.data.database.dao.grants

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GrantOptionItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrantOptionItem(grantOptionItem: GrantOptionItemsEntity)

    @Update
    suspend fun updateGrantOptionItem(grantOptionItem: GrantOptionItemsEntity)

    @Delete
    suspend fun deleteGrantOptionItem(grantOptionItem: GrantOptionItemsEntity)

    @Query("SELECT * FROM grant_option_items WHERE id = :grantOptionItemId")
    fun getGrantOptionItemById(grantOptionItemId: String): Flow<GrantOptionItemsEntity?>

    @Query("SELECT * FROM grant_option_items WHERE granter_option_set_id = :granterOptionSetId")
    fun getGrantOptionItemsByGranterOptionSetId(granterOptionSetId: String): Flow<List<GrantOptionItemsEntity>>

    @Query("SELECT * FROM grant_option_items WHERE granted_content_id = :grantedContentId")
    fun getGrantOptionItemsByGrantedContentId(grantedContentId: String): Flow<List<GrantOptionItemsEntity>>

    @Query("SELECT * FROM grant_option_items")
    fun getAllGrantOptionItems(): Flow<List<GrantOptionItemsEntity>>
}