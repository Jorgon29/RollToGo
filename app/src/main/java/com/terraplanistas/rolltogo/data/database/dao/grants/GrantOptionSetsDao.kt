package com.terraplanistas.rolltogo.data.database.dao.grants

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantOptionSetsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GrantOptionSetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrantOptionSet(grantOptionSet: GrantOptionSetsEntity)

    @Update
    suspend fun updateGrantOptionSet(grantOptionSet: GrantOptionSetsEntity)

    @Delete
    suspend fun deleteGrantOptionSet(grantOptionSet: GrantOptionSetsEntity)

    @Query("SELECT * FROM grant_option_sets WHERE id = :grantOptionSetId")
    fun getGrantOptionSetById(grantOptionSetId: String): Flow<GrantOptionSetsEntity?>

    @Query("SELECT * FROM grant_option_sets WHERE granter_content_id = :granterContentId")
    fun getGrantOptionSetsByGranterContentId(granterContentId: String): Flow<List<GrantOptionSetsEntity>>

    @Query("SELECT * FROM grant_option_sets")
    fun getAllGrantOptionSets(): Flow<List<GrantOptionSetsEntity>>
}