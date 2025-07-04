@file:OptIn(ExperimentalMaterial3Api::class)

package com.terraplanistas.rolltogo.ui.screens.baseHomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle

@Composable
fun BaseHomeScreen(
    navController: NavController,
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit,
    snackBarHostState: SnackbarHostState? = null,
    showCampaignModal: Boolean = false,
    hideCampaingModal: (Boolean) -> Unit = {},
) {
    var campaignSheetState = rememberModalBottomSheetState()
    var newCampaignName = rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(listOf<Color>(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.surfaceContainer),
                            radius = 1300f
                        )),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let{
                    BasicTitle(title)
                }
                content()

                if(showCampaignModal){
                    NewCampaignModal(
                        hideModal = {hideCampaingModal(false)},
                        sheetState = campaignSheetState,
                        newCampaignName = newCampaignName.value,
                        changeNewCampaingName = { newCampaignName.value = it}
                    )
                }
            }

        }


