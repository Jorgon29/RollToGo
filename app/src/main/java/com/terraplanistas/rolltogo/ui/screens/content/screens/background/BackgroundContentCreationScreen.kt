package com.terraplanistas.rolltogo.ui.screens.content.screens.background

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.BackgroundBasicInfoSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.FeaturesSelectionSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.ProficienciesSelectionSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.SaveButton

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun BackgroundContentCreationScreen(
    viewModel: BackgroundCreationViewModel = viewModel(factory = BackgroundCreationViewModel.factory),
    nav: NavHostController
) {
    val uiState = viewModel.uiState.collectAsState()
    val proficiencies = viewModel.availableProficiencies.collectAsState()
    val features = viewModel.availableFeatures.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .heightIn(max = LocalConfiguration.current.screenHeightDp.dp)
                .padding(16.dp)
        ) {
            BackgroundBasicInfoSection(
                name = uiState.value.formData["name"]?.toString() ?: "",
                description = uiState.value.formData["description"]?.toString() ?: "",
                onNameChange = viewModel::updateName,
                onDescriptionChange = viewModel::updateDescription
            )

            Column {
                ProficienciesSelectionSection(
                    proficiencies = proficiencies.value,
                    selectedProficiencies = uiState.value.formData["proficiencies"] as? List<Map<String, String>>
                        ?: emptyList(),
                    onProficiencySelected = viewModel::toggleProficiency
                )

                FeaturesSelectionSection(
                    features = features.value,
                    selectedFeatureIds = uiState.value.formData["feature_ids"] as? List<String>
                        ?: emptyList(),
                    onFeatureSelected = viewModel::toggleFeature
                )
            }

            SaveButton(
                enabled = viewModel.getStrategy().validateContent(uiState.value.formData),
                onClick = {
                    Log.d(
                        "BackgroundCreationScreen",
                        "Saving background with data: ${uiState.value.formData}"
                    )
                }
            )
        }
    }
}




