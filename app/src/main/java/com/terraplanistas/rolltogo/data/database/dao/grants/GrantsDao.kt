package com.terraplanistas.rolltogo.data.database.dao.grants

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.terraplanistas.rolltogo.data.database.entities.grants.GrantsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GrantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrant(grant: GrantsEntity)

    @Update
    suspend fun updateGrant(grant: GrantsEntity)

    @Delete
    suspend fun deleteGrant(grant: GrantsEntity)

    @Query("SELECT * FROM grants WHERE id = :grantId")
    fun getGrantById(grantId: String): Flow<GrantsEntity?>

    @Query("SELECT * FROM grants WHERE granter_content_id = :granterContentId")
    fun getGrantsByGranterContentId(granterContentId: String): Flow<List<GrantsEntity>>

    @Query("SELECT * FROM grants WHERE granted_content_id = :grantedContentId")
    fun getGrantsByGrantedContentId(grantedContentId: String): Flow<List<GrantsEntity>>

    @Query("SELECT * FROM grants WHERE granter_type_enum = :granterType")
    fun getGrantsByGranterType(granterType: String): Flow<List<GrantsEntity>>

    @Query("SELECT * FROM grants WHERE granted_type = :grantedType")
    fun getGrantsByGrantedType(grantedType: String): Flow<List<GrantsEntity>>

    @Query("SELECT * FROM grants")
    fun getAllGrants(): Flow<List<GrantsEntity>>
}