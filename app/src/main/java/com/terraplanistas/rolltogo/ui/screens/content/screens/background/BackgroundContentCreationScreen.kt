package com.terraplanistas.rolltogo.ui.screens.content.screens.background

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.data.enums.AbilityTypeEnum
import com.terraplanistas.rolltogo.ui.navigations.ForumNavigation
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.BackgroundBasicInfoSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.FeaturesSelectionSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.ProficienciesSelectionSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.SaveButton

@Composable
fun BackgroundContentCreationScreen(
    viewModel: BackgroundCreationViewModel = viewModel(factory = BackgroundCreationViewModel.factory),
    nav: NavHostController
) {
    val uiState = viewModel.uiState.collectAsState()
    val proficiencies = viewModel.availableProficiencies.collectAsState()
    val features = viewModel.availableFeatures.collectAsState()
    val isValid by viewModel.isValid.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text("Error") },
            text = { Text(errorMessage!!) },
            confirmButton = {
                Button(
                    onClick = { viewModel.clearError() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(109, 126, 168)
                    )
                ) {
                    Text("Aceptar")
                }
            }
        )
    }

    if (isValid) {
        AlertDialog(
            onDismissRequest = { viewModel.clearError() },
            title = { Text("Creado con exito!") },
            text = { Text("Su Background fue creada con exito") },
            confirmButton = {
                Button(
                    onClick = { nav.navigate(ForumNavigation) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(109, 126, 168)
                    )
                ) {
                    Text("Volver a foros")
                }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.continueWithDefaultData() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(109, 126, 168)
                    )
                ) {
                    Text("Seguir creando!")
                }
            }


        )

    }


    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(72, 94, 146))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }


    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(255, 255, 255)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    AbilityTypeEnum
                    BackgroundBasicInfoSection(
                        name = uiState.value.formData["name"]?.toString() ?: "",
                        description = uiState.value.formData["description"]?.toString() ?: "",
                        onNameChange = viewModel::updateName,
                        onDescriptionChange = viewModel::updateDescription
                    )
                }

                item {
                    ProficienciesSelectionSection(
                        proficiencies = proficiencies.value,
                        selectedProficiencies = uiState.value.formData["proficiencies"] as? List<Map<String, String>>
                            ?: emptyList(),
                        onProficiencySelected = viewModel::toggleProficiency
                    )
                }

                item {
                    FeaturesSelectionSection(
                        features = features.value,
                        selectedFeatureIds = uiState.value.formData["feature_ids"] as? List<String>
                            ?: emptyList(),
                        onFeatureSelected = viewModel::toggleFeature
                    )
                }

                item {
                    SaveButton(
                        enabled = viewModel.getStrategy().validateContent(uiState.value.formData),
                        onClick = {
                            viewModel.submit()

                        }
                    )
                }
            }
        }
    }


}




