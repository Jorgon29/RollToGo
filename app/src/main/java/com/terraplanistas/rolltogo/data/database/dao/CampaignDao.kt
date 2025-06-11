package com.terraplanistas.rolltogo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terraplanistas.rolltogo.data.database.entities.CampaignEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CampaignDao {
    @Query("SELECT * FROM campaign")
    fun getCampaigns(): Flow<List<CampaignEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCampaign(campaignEntity: CampaignEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCampaigns(campaings: List<CampaignEntity>)

    @Delete
    suspend fun removeCampaign(campaignEntity: CampaignEntity)
}