package com.terraplanistas.rolltogo.ui.screens.campaignList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox
import com.terraplanistas.rolltogo.ui.layout.boxes.roomCard.RoomCard

@Composable
fun CampaignListScreen(
    viewModel: CampaignListViewModel = viewModel(factory = CampaignListViewModel.factory),
    //onCampaignSelected: (String) -> Unit,
    //onCreateCampaign: () -> Unit,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.loadMyCampaigns()
    }

    val campaigns = viewModel.campaigns.collectAsState().value

    Log.d("CampaignListScreen", "Campaigns: ${campaigns.toString()}")

    CampaignListContent(
        campaigns = campaigns
        //onCampaignSelected = onCampaignSelected,
        //onCreateCampaign = onCreateCampaign
    )
}

@Composable
fun CampaignListContent(
    campaigns: Resource<List<RoomDomain>>,
    //onCampaignSelected: (String) -> Unit,
    //onCreateCampaign: () -> Unit
) {
    when (campaigns) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            val campaignList = campaigns.data
            if (campaignList.isEmpty()) {
                // Show empty state
            } else {
                // Show list of campaigns
                CampaignList(
                    campaigns = campaignList
                    //onCampaignSelected = onCampaignSelected
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
    onCampaignSelected: (String) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(campaigns) {
            campaign: RoomDomain ->
            RoomCard(
                name = campaign.name,
                description = campaign.description,
                ownerUsername = campaign.ownerUserName,
                onClick = { onCampaignSelected(campaign.id) }
            )
        }
    }
}
