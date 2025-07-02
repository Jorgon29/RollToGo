package com.terraplanistas.rolltogo.ui.screens.content.screens.features

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum
import com.terraplanistas.rolltogo.data.enums.BonusTypeEnum
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import com.terraplanistas.rolltogo.ui.screens.content.screens.features.components.ActionTypeDropdown
import com.terraplanistas.rolltogo.ui.screens.content.screens.features.components.BonusSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.features.components.DamageSection
import com.terraplanistas.rolltogo.ui.screens.content.screens.features.components.FeatureToggles


@Composable
fun FeatureCreationScreen(
    viewModel: FeatureCreationViewModel = viewModel(factory = FeatureCreationViewModel.factory),
    nav: NavHostController,
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .background(Color(237, 238, 244))
    ) {
        //
        Text(
            text = "Crear una nueva feature",
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
                .padding(horizontal = 8.dp)
        ) {
            OutlinedTextField(
                value = uiState.formData["name"].toString(),
                onValueChange = { viewModel.updateField("name", it) },
                label = {
                    Text(
                        "Nombre de la feature*",
                        color = Color(72, 94, 146) // blue-500
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.formData["name"].toString().isEmpty(),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(237, 238, 244), // blue-50
                    unfocusedContainerColor = Color(237, 238, 244), // blue-50
                    focusedIndicatorColor = Color(109, 126, 168), // blue-400
                    unfocusedIndicatorColor = Color(171, 181, 209) // blue-200
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.formData["description"].toString(),
                onValueChange = { viewModel.updateField("description", it) },
                label = {
                    Text(
                        "Descripción*",
                        color = Color(72, 94, 146)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                isError = uiState.formData["description"].toString().isEmpty(),
                maxLines = 5,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(237, 238, 244),
                    unfocusedContainerColor = Color(237, 238, 244),
                    focusedIndicatorColor = Color(109, 126, 168),
                    unfocusedIndicatorColor = Color(171, 181, 209)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))


            Spacer(modifier = Modifier.height(16.dp))

            FeatureToggles(
                isMagical = uiState.formData["is_magical"] as Boolean,
                isPassive = uiState.formData["is_passive"] as Boolean,
                onMagicalChanged = { viewModel.updateField("is_magical", it) },
                onPassiveChanged = { viewModel.updateField("is_passive", it) }
            )

            if (!(uiState.formData["is_passive"] as Boolean)) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))

                    ActionTypeDropdown(
                        currentValue = uiState.formData["action_type"].toString(),
                        onValueSelected = { viewModel.updateField("action_type", it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DamageSection(
                        damage = uiState.formData["damage"].toString(),
                        damageType = uiState.formData["damage_type"].toString(),
                        onDamageChanged = { viewModel.updateField("damage", it) },
                        onDamageTypeSelected = { viewModel.updateField("damage_type", it) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    BonusSection(
                        bonus = uiState.formData["bonus"].toString(),
                        bonusType = uiState.formData["bonus_type"].toString(),
                        onBonusChanged = { viewModel.updateField("bonus", it) },
                        onBonusTypeChanged = { viewModel.updateField("bonus_type", it) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.submitContent()
                Log.d("Values saved", uiState.formData.toString())
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 8.dp),
            enabled = uiState.isValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(109, 126, 168),
                disabledContainerColor = Color(171, 181, 209),
                contentColor = Color.White,
                disabledContentColor = Color.White.copy(alpha = 0.5f)
            )
        ) {
            Text("Crear feature", style = MaterialTheme.typography.labelLarge)
        }
    }
}
