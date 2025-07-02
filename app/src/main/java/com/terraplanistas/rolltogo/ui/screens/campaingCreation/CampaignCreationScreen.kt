package com.terraplanistas.rolltogo.ui.screens.campaingCreation

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun CampaignCreationScreen(
    navController: NavHostController,
    viewModel: CampaignCreationViewModel = viewModel(factory = CampaignCreationViewModel.factory)
) {
    val uiState = viewModel.campaignState.collectAsState()
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    var showSuccessDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = {
                Text(
                    "Sala creada",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color(30, 38, 81) // blue-900
                    )
                )
            },
            text = {
                Text(
                    "La sala se ha creado correctamente. Puedes volver al menú principal.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(72, 94, 146)
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(109, 126, 168),
                        contentColor = Color.White
                    )
                ) {
                    Text("Volver al menú")
                }
            },
            modifier = Modifier.background(Color(237, 238, 244))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .background(Color(237, 238, 244))
    ) {

        Text(
            text = "Crear Nueva Sala",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(30, 38, 81)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 55.dp)
        ) {
            // Nombre de la sala
            OutlinedTextField(
                value = uiState.value.name,
                onValueChange = { viewModel.updateName(it) },
                label = {
                    Text(
                        "Nombre de la Sala*",
                        color = Color(72, 94, 146) // blue-500
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.value.name.isEmpty(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(237, 238, 244), // blue-50
                    unfocusedContainerColor = Color(237, 238, 244), // blue-50
                    focusedIndicatorColor = Color(109, 126, 168), // blue-400
                    unfocusedIndicatorColor = Color(171, 181, 209) // blue-200
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Descripción
            OutlinedTextField(
                value = uiState.value.description,
                onValueChange = { viewModel.updateDescription(it) },
                label = {
                    Text(
                        "Descripción",
                        color = Color(72, 94, 146) // blue-500
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                maxLines = 5,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(237, 238, 244), // blue-50
                    unfocusedContainerColor = Color(237, 238, 244), // blue-50
                    focusedIndicatorColor = Color(109, 126, 168), // blue-400
                    unfocusedIndicatorColor = Color(171, 181, 209) // blue-200
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

        }

        // Botón de creación al final
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                //viewModel.createCampaign()
                showSuccessDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 8.dp),
            enabled = uiState.value.name.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(109, 126, 168), // blue-400
                disabledContainerColor = Color(171, 181, 209), // blue-200
                contentColor = Color.White,
                disabledContentColor = Color.White.copy(alpha = 0.5f)
            )
        ) {
            Text("Crear Sala", style = MaterialTheme.typography.labelLarge)
        }
    }
}
