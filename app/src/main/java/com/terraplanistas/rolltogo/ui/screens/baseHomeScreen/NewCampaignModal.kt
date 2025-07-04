package com.terraplanistas.rolltogo.ui.screens.baseHomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCampaignModal(
    hideModal: () -> Unit,
    sheetState: SheetState,
    newCampaignName: String,
    changeNewCampaingName: (String) -> Unit
){
    ModalBottomSheet(
        onDismissRequest = {hideModal()},
        sheetState = sheetState,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(Modifier.height(16.dp))
            Text(stringResource(R.string.new_campaign))
            HorizontalDivider()
            Spacer(Modifier.height(16.dp))
            TextField(
                value = newCampaignName,
                onValueChange = {changeNewCampaingName(it)},
                placeholder = {Text(stringResource(R.string.actor_creation_biography_name))}
            )
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {hideModal();changeNewCampaingName("")}
                ) {
                    Text(stringResource(R.string.cancel))
                }
                Button(
                    onClick = {}
                ) {
                    Text(stringResource(R.string.create))
                }
            }
            Spacer(Modifier.height(96.dp))

        }

    }
}