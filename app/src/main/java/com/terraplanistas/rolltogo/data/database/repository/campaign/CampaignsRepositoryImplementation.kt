package com.terraplanistas.rolltogo.data.database.repository.campaign

import com.terraplanistas.rolltogo.data.database.dao.CampaignDao
import com.terraplanistas.rolltogo.data.database.entities.toDomain
import com.terraplanistas.rolltogo.data.database.repository.BaseRepository
import com.terraplanistas.rolltogo.data.model.Campaign
import com.terraplanistas.rolltogo.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CampaignsRepositoryImplementation(
    private val campaignDao: CampaignDao
): BaseRepository<Campaign> {
    override fun getElements(): Flow<List<Campaign>> {
        return campaignDao.getCampaigns().map { it.map { it.toDomain() } }
    }

    override suspend fun addElement(t: Campaign) {
        campaignDao.addCampaign(t.toEntity())
    }

    override suspend fun addElements(ts: List<Campaign>) {
        campaignDao.addCampaigns(ts.map { it.toEntity() })
    }

    override suspend fun removeElement(t: Campaign) {
        campaignDao.removeCampaign(t.toEntity())
    }

}