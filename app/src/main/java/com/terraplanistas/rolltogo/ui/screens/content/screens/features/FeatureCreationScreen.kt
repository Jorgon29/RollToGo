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
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum


@Composable
fun FeatureCreationScreen(
    viewModel: FeatureCreationViewModel = viewModel(factory = FeatureCreationViewModel.factory),
    nav: NavHostController,
) {
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
            .background(Color(237, 238, 244)) // blue-50
    ) {
        // Título
        Text(
            text = "Create New Feature",
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color(30, 38, 81) // blue-900
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            textAlign = TextAlign.Center
        )

        // Campos del formulario
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
                        "Feature Name*",
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
                        "Description*",
                        color = Color(72, 94, 146) // blue-500
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp),
                isError = uiState.formData["description"].toString().isEmpty(),
                maxLines = 5,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(237, 238, 244), // blue-50
                    unfocusedContainerColor = Color(237, 238, 244), // blue-50
                    focusedIndicatorColor = Color(109, 126, 168), // blue-400
                    unfocusedIndicatorColor = Color(171, 181, 209) // blue-200
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            FeatureTypeDropdown(
                currentValue = uiState.formData["feature_type"].toString(),
                onValueSelected = { viewModel.updateField("feature_type", it) },
                modifier = Modifier.fillMaxWidth()
            )

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

        // Botón de submit al final
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
                containerColor = Color(109, 126, 168), // blue-400
                disabledContainerColor = Color(171, 181, 209), // blue-200
                contentColor = Color.White,
                disabledContentColor = Color.White.copy(alpha = 0.5f)
            )
        ) {
            Text("Create Feature", style = MaterialTheme.typography.labelLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeatureTypeDropdown(
    currentValue: String,
    onValueSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Class", "Race", "Feat", "Background", "Other")

    Box(modifier = modifier) {

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = currentValue,
                onValueChange = {},
                label = {
                    Text(
                        "Feature Type",
                        color = Color(72, 94, 146)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,

                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, null, tint = Color(72, 94, 146))
                },
                interactionSource = remember { MutableInteractionSource() },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(237, 238, 244), // blue-50
                    unfocusedContainerColor = Color(237, 238, 244), // blue-50
                    focusedIndicatorColor = Color(109, 126, 168), // blue-400
                    unfocusedIndicatorColor = Color(171, 181, 209) // blue-200
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }

            ) {

                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onValueSelected(option)
                            expanded = false
                        }
                    )
                }
            }


        }

    }
}

@Composable
private fun FeatureToggles(
    isMagical: Boolean,
    isPassive: Boolean,
    onMagicalChanged: (Boolean) -> Unit,
    onPassiveChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            "Feature Properties",
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(72, 94, 146) // blue-500
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Switch(
                checked = isMagical,
                onCheckedChange = onMagicalChanged,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(109, 126, 168), // blue-400
                    checkedTrackColor = Color(171, 181, 209), // blue-200
                    uncheckedThumbColor = Color(189, 205, 221), // blue-100
                    uncheckedTrackColor = Color(237, 238, 244) // blue-50
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Magical Feature",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(30, 38, 81) // blue-900
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Switch(
                checked = isPassive,
                onCheckedChange = onPassiveChanged,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(109, 126, 168), // blue-400
                    checkedTrackColor = Color(171, 181, 209), // blue-200
                    uncheckedThumbColor = Color(189, 205, 221), // blue-100
                    uncheckedTrackColor = Color(237, 238, 244) // blue-50
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Passive Feature",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(30, 38, 81) // blue-900
                )
            )
        }
    }
}


@Composable
private fun ActionTypeDropdown(
    currentValue: String,
    onValueSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val options = ActionTypeEnum.entries.map { it.value.lowercase() }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = currentValue,
            onValueChange = {},
            label = { Text("Action Type") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, null)
            },
            interactionSource = remember { MutableInteractionSource() }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun DamageSection(
    damage: String,
    damageType: String,
    onDamageChanged: (String) -> Unit,
    onDamageTypeSelected: (String) -> Unit
) {
    Column {
        Text("Damage Information", style = MaterialTheme.typography.labelMedium)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = damage,
            onValueChange = onDamageChanged,
            label = { Text("Damage (e.g., 1d6)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DamageTypeDropdown(
            currentValue = damageType,
            onValueSelected = onDamageTypeSelected,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DamageTypeDropdown(
    currentValue: String,
    onValueSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val damageTypes = DamageTypeEnum.entries.map { it.value.lowercase() }

    Box(modifier = modifier) {
        ExposedDropdownMenuBox(
            expanded =expanded,
            onExpandedChange = {expanded = !expanded }
        ) {
            OutlinedTextField(
                value = currentValue,
                onValueChange = {},
                label = { Text("Damage Type") },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, null)
                },
                interactionSource = remember { MutableInteractionSource() }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                damageTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            onValueSelected(type)
                            expanded = false
                        },
                        colors = MenuItemColors(
                            textColor = Color.White,
                            leadingIconColor = Color.White,
                            trailingIconColor = Color(255, 255, 255),
                            disabledTextColor = Color(255, 255, 255,),
                            disabledLeadingIconColor = Color(255, 255, 255),
                            disabledTrailingIconColor = Color(255,255,255), // semi-transparent white
                        )
                    )
                }
            }

        }
    }
}

@Composable
private fun BonusSection(
    bonus: String,
    bonusType: String,
    onBonusChanged: (String) -> Unit,
    onBonusTypeChanged: (String) -> Unit
) {
    Column {
        Text("Bonus Information", style = MaterialTheme.typography.labelMedium)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bonus,
            onValueChange = onBonusChanged,
            label = { Text("Bonus (e.g., +2)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bonusType,
            onValueChange = onBonusTypeChanged,
            label = { Text("Bonus Type (e.g., Attack Rolls)") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}