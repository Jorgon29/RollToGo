package com.terraplanistas.rolltogo.ui.screens.campaignList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.data.model.room.RoomDomain
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox
import com.terraplanistas.rolltogo.ui.layout.boxes.roomCard.RoomCard
import com.terraplanistas.rolltogo.ui.navigations.ForumNavigation

@Composable
fun CampaignListScreen(
    viewModel: CampaignListViewModel = viewModel(factory = CampaignListViewModel.factory),
    navController: NavController
) {
    val showJoin by viewModel.showJoin.collectAsState()
    val showJoinDialog by viewModel.showJoinDialog.collectAsState()
    val joinCode by viewModel.joinCode.collectAsState()
    val campaigns = viewModel.campaigns.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.loadMyCampaigns()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Diálogo de éxito al unirse
        if (showJoin) {
            AlertDialog(
                onDismissRequest = { viewModel.switchIsShowing(false) },
                title = { Text("¡Unido con éxito!") },
                text = { Text("Te has unido a la campaña con éxito") },
                confirmButton = {
                    Button(
                        onClick = { viewModel.switchIsShowing(false) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(109, 126, 168)
                        )
                    ) {
                        Text("Aceptar")
                    }
                },
            )
        }

        // Diálogo para ingresar código
        if (showJoinDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.showJoinDialog(false) },
                title = { Text("Unirse a una campaña") },
                text = {
                    Column {
                        Text("Ingresa el código de la campaña:")
                        TextField(
                            value = joinCode,
                            onValueChange = { viewModel.updateJoinCode(it) },
                            placeholder = { Text("Ingresa el codigo de tu campaña") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { viewModel.joinCampaign() },
                        enabled = joinCode.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(109, 126, 168)
                        )
                    ) {
                        Text("Unirse")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { viewModel.joinCampaign() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(109, 126, 168)
                        )
                    ) {
                        Text("Salir")
                    }
                }
            )
        }

        CampaignListContent(
            campaigns = campaigns,
            onJoinCampaign = { viewModel.showJoinDialog(true) }
        )
    }
}

@Composable
fun CampaignListContent(
    campaigns: Resource<List<RoomDomain>>,
    onJoinCampaign: () -> Unit
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
            Box(
                modifier = Modifier
                    .background(Color(72, 94, 146))
                    .fillMaxSize()
            ) {
                if (campaignList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No tienes campañas aún")
                    }
                } else {
                    CampaignList(campaigns = campaignList)
                }

                // Botón para unirse a campaña en la parte inferior
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = onJoinCampaign,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(109, 126, 168)
                        )
                    ) {
                        Text("Unirse a campaña")
                    }
                }
            }
        }

        is Resource.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error al cargar campañas")
            }
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
        items(campaigns) { campaign: RoomDomain ->
            RoomCard(
                name = campaign.name,
                description = campaign.description,
                ownerUsername = campaign.ownerUserName,
                onClick = { onCampaignSelected(campaign.id) }
            )
        }
    }
}
