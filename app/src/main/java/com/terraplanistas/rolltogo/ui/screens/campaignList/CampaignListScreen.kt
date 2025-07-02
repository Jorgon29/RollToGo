package com.terraplanistas.rolltogo.ui.screens.campaignList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox

@Composable
fun CampaignListScreen(
    viewModel: CampaignListViewModel,
    onCampaignSelected: (String) -> Unit,
    onCreateCampaign: () -> Unit
) {
    val campaigns = viewModel.campaigns.collectAsState().value

    CampaignListContent(
        campaigns = campaigns,
        onCampaignSelected = onCampaignSelected,
        onCreateCampaign = onCreateCampaign
    )
}

@Composable
fun CampaignListContent(
    campaigns: Resource<List<RoomDomain>>,
    onCampaignSelected: (String) -> Unit,
    onCreateCampaign: () -> Unit
) {
    when (campaigns) {
        is Resource.Loading -> {
            // Show loading indicator
        }
        is Resource.Success -> {
            val campaignList = campaigns.data
            if (campaignList.isEmpty()) {
                // Show empty state
            } else {
                // Show list of campaigns
                CampaignList(
                    campaigns = campaignList,
                    onCampaignSelected = onCampaignSelected,
                    onCreateCampaign = onCreateCampaign
                )
            }
        }
        is Resource.Error -> {
            // Show error message
        }
    }
}

@Composable
fun CampaignList(
    campaigns: List<RoomDomain>,
    onCampaignSelected: (String) -> Unit,
    onCreateCampaign: () -> Unit
) {

    LazyColumn {
        items(campaigns.size) { index ->
            val campaign = campaigns[index]
            CategoryBox(
                title = campaign.name,
                content = campaign.description,
                onClick = { onCampaignSelected(campaign.id) },
                onCreateCampaign = onCreateCampaign
            )
        }
    }
}