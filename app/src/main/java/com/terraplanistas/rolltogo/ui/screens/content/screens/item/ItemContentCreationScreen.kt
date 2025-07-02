package com.terraplanistas.rolltogo.ui.screens.content.screens.item

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terraplanistas.rolltogo.data.enums.ActionTypeEnum
import com.terraplanistas.rolltogo.data.enums.CurrencyEnum
import com.terraplanistas.rolltogo.data.enums.DamageTypeEnum
import com.terraplanistas.rolltogo.data.enums.ItemTypeEnum
import com.terraplanistas.rolltogo.data.enums.RarityEnum
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.ui.screens.content.screens.item.ItemCreationViewModel
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemContentCreationScreen(
    viewModel: ItemCreationViewModel = viewModel(factory = ItemCreationViewModel.factory),
    nav: NavHostController
) {


    val formData = viewModel.uiState.collectAsState()

    fun updateFormData(key: String, value: Any) {
        val updatedMap = formData.value.formData.toMutableMap().apply {
            put(key, value)
        }
        viewModel.updateFormData(updatedMap)
    }

    val rarityOptions = RarityEnum.entries.map { it.value }
    val itemTypeOptions = ItemTypeEnum.entries.map { it.value }
    val currencyOptions = CurrencyEnum.entries.map { it.value }
    val damageTypes = DamageTypeEnum.entries.map { it.value }
    val actionTypes = ActionTypeEnum.entries.map { it.value }
    val tagOptions = listOf(
        "Plateada",
        "Dos-manos",
        "Ligera",
        "Fina",
        "Recargable",
        "Arrojable",
        "Pesada",
        "Versatil"
    )

    if (formData.value.formData.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Text(
                text = "Crear Nueva Arma",
                style = MaterialTheme.typography.bodySmall,
                color = Color(50, 50, 50),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Sección de información básica
            Text(
                text = "Información Básica",
                style = MaterialTheme.typography.bodySmall,
                color = Color(70, 130, 180)
            )

            // Nombre del arma
            OutlinedTextField(
                value = formData.value.formData["name"].toString(),
                onValueChange = { updateFormData("name", it) },
                label = { Text("Nombre del arma*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors(),
                singleLine = true
            )

            // Descripción
            OutlinedTextField(
                value = formData.value.formData["description"].toString(),
                onValueChange = { updateFormData("description", it) },
                label = { Text("Descripción*") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors(),
                maxLines = 5
            )

            // Tipo y Rareza
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Tipo de item
                Box(modifier = Modifier.weight(1f)) {
                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = formData.value.formData["item_type"].toString(),
                            onValueChange = {},
                            label = { Text("Tipo de arma*") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = textFieldColors(),
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            itemTypeOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option.toString()) },
                                    onClick = {
                                        updateFormData("item_type", option)
                                        expanded = false
                                    },
                                    colors = MenuItemColors(
                                        textColor = Color.White,
                                        leadingIconColor = Color.White,
                                        trailingIconColor = Color(255, 255, 255),
                                        disabledTextColor = Color(255, 255, 255),
                                        disabledLeadingIconColor = Color(255, 255, 255),
                                        disabledTrailingIconColor = Color(255, 255, 255),
                                    )

                                )
                            }
                        }
                    }
                }

                // Rareza
                Box(modifier = Modifier.weight(1f)) {
                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = formData.value.formData["rarity"].toString(),
                            onValueChange = {},
                            label = { Text("Rareza*") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = textFieldColors(),
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            rarityOptions.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        updateFormData("rarity", option)
                                        expanded = false
                                    },

                                    text = { Text(text = option.toString()) }
                                    ,
                                    colors = MenuItemColors(
                                        textColor = Color.White,
                                        leadingIconColor = Color.White,
                                        trailingIconColor = Color(255, 255, 255),
                                        disabledTextColor = Color(255, 255, 255),
                                        disabledLeadingIconColor = Color(255, 255, 255),
                                        disabledTrailingIconColor = Color(255, 255, 255),
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Peso y Costo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Peso
                OutlinedTextField(
                    value = formData.value.formData["weight"].toString(),
                    onValueChange = {
                        updateFormData(
                            "weight",
                            it.toBigDecimalOrNull() ?: BigDecimal.ZERO
                        )
                    },
                    label = { Text("Peso*") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                // Valor del costo
                OutlinedTextField(
                    value = formData.value.formData["cost_value"].toString(),
                    onValueChange = {
                        updateFormData(
                            "cost_value",
                            it.toBigDecimalOrNull() ?: BigDecimal.ZERO
                        )
                    },
                    label = { Text("Valor*") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            // Moneda y Atributos
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Moneda
                Box(modifier = Modifier.weight(1f)) {
                    var expanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = formData.value.formData["cost_unit"].toString(),
                            onValueChange = {},
                            label = { Text("Moneda*") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = textFieldColors(),
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            currencyOptions.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        updateFormData("cost_unit", option)
                                        expanded = false
                                    },
                                    text = { Text(text = option) }
                                    ,
                                    colors = MenuItemColors(
                                        textColor = Color.White,
                                        leadingIconColor = Color.White,
                                        trailingIconColor = Color(255, 255, 255),
                                        disabledTextColor = Color(255, 255, 255),
                                        disabledLeadingIconColor = Color(255, 255, 255),
                                        disabledTrailingIconColor = Color(255, 255, 255),
                                    )
                                )
                            }
                        }
                    }
                }

                // Bonificación al ataque
                OutlinedTextField(
                    value = formData.value.formData["attack_bonus"].toString(),
                    onValueChange = { updateFormData("attack_bonus", it.toIntOrNull() ?: 0) },
                    label = { Text("Bonif. Ataque*") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            // Sección de atributos especiales
            Text(
                text = "Atributos Especiales",
                style = MaterialTheme.typography.bodySmall,
                color = Color(70, 130, 180)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Es mágico
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = formData.value.formData["is_magical"] as? Boolean ?: false,
                        onCheckedChange = { updateFormData("is_magical", it) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(70, 130, 180),
                            uncheckedColor = Color(150, 150, 150)
                        )
                    )
                    Text("Es mágico", modifier = Modifier.padding(start = 4.dp))
                }

                // Requiere sintonización
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = formData.value.formData["attunment"] as? Boolean ?: false,
                        onCheckedChange = { updateFormData("attunment", it) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(70, 130, 180),
                            uncheckedColor = Color(150, 150, 150)
                        )
                    )
                    Text("Requiere sintonización", modifier = Modifier.padding(start = 4.dp))
                }
            }

            // Sección de características de combate
            Text(
                text = "Características de Combate",
                style = MaterialTheme.typography.bodySmall,
                color = Color(70, 130, 180)
            )

            // Daño y Tipo de daño
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Daño
                OutlinedTextField(
                    value = formData.value.formData["damage"].toString(),
                    onValueChange = { updateFormData("damage", it) },
                    label = { Text("Daño*") },
                    modifier = Modifier.weight(1f),
                    colors = textFieldColors(),
                    singleLine = true
                )

                // Tipo de daño
                Box(modifier = Modifier.weight(1f)) {
                    var expanded by remember { mutableStateOf(false) }
                    val selectedDamageType =
                        formData.value.formData["damage_tipe"]?.toString() ?: ""

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedDamageType,
                            onValueChange = {},
                            label = { Text("Tipo de daño*") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = textFieldColors(),
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            damageTypes.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        updateFormData("damage_tipe", option)
                                        expanded = false
                                    },
                                    text = { Text(text = option) },
                                    colors = MenuItemColors(
                                        textColor = Color.White,
                                        leadingIconColor = Color.White,
                                        trailingIconColor = Color(255, 255, 255),
                                        disabledTextColor = Color(255, 255, 255),
                                        disabledLeadingIconColor = Color(255, 255, 255),
                                        disabledTrailingIconColor = Color(255, 255, 255),
                                    )
                                )
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    var expanded by remember { mutableStateOf(false) }
                    val selectedActiontype =
                        formData.value.formData["action_type"]?.toString() ?: ""

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedActiontype,
                            onValueChange = {},
                            label = { Text("Tipo de acción*") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = textFieldColors(),
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            actionTypes.forEach { option ->
                                DropdownMenuItem(
                                    onClick = {
                                        updateFormData("action_type", option)
                                        expanded = false
                                    },
                                    text = { Text(text = option) },
                                    colors = MenuItemColors(
                                        textColor = Color.White,
                                        leadingIconColor = Color.White,
                                        trailingIconColor = Color(255, 255, 255),
                                        disabledTextColor = Color(255, 255, 255),
                                        disabledLeadingIconColor = Color(255, 255, 255),
                                        disabledTrailingIconColor = Color(255, 255, 255),
                                    )
                                )
                            }
                        }
                    }
                }

            }

            // Sección de etiquetas
            Text(
                text = "Etiquetas del Arma",
                style = MaterialTheme.typography.bodySmall,
                color = Color(70, 130, 180)
            )

            // Etiquetas (selección múltiple)
            val currentTags = (formData.value.formData["item_tag"] as? List<String>) ?: emptyList()

            Column(modifier = Modifier.fillMaxWidth()) {
                tagOptions.forEach { tag ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = currentTags.contains(tag),
                            onCheckedChange = { isChecked ->
                                val newTags = if (isChecked) {
                                    currentTags + tag
                                } else {
                                    currentTags - tag
                                }
                                updateFormData("item_tag", newTags)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(70, 130, 180),
                                uncheckedColor = Color(150, 150, 150)
                            )
                        )
                        Text(
                            text = tag,
                            modifier = Modifier.padding(start = 8.dp),
                            color = Color(50, 50, 50)
                        )
                    }
                }
            }

            // Botón de guardar
            Button(
                onClick = {
                    if (viewModel.currentStrategy.validateContent(formData.value.formData)) {
                        // viewModel.currentStrategy?.sumbit(formData.value.formData, viewModel.getRepository())
                        //nav.popBackStack()
                        Log.d(
                            "Item",
                            "Item guardado: ${formData.value.formData["name"]} descripción: ${formData.value.formData["description"]}" +
                                    " tipo: ${formData.value.formData["item_type"]} rareza: ${formData.value.formData["rarity"]}" +
                                    " peso: ${formData.value.formData["weight"]} valor: ${formData.value.formData["cost_value"]}" +
                                    " moneda: ${formData.value.formData["cost_unit"]} ataque: ${formData.value.formData["attack_bonus"]}" +
                                    " daño: ${formData.value.formData["damage"]} tipo de daño: ${formData.value.formData["damage_tipe"]}" +
                                    " es mágico: ${formData.value.formData["is_magical"]} requiere sintonización: ${formData.value.formData["attunment"]}" +
                                    " etiquetas: ${formData.value.formData["item_tag"]} tipo de acción: ${formData.value.formData["action_type"]}"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(70, 130, 180),
                    contentColor = Color.White
                ),
                enabled = viewModel.currentStrategy?.validateContent(formData.value.formData)
                    ?: false
            ) {
                Text("Guardar Item")
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()

        }
    }
}

@Composable
private fun textFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedTextColor = Color(50, 50, 50),
        unfocusedTextColor = Color(50, 50, 50),
        disabledTextColor = Color(150, 150, 150),
        errorTextColor = Color.Red,
        focusedContainerColor = Color(245, 245, 245),
        unfocusedContainerColor = Color(245, 245, 245),
        disabledContainerColor = Color(245, 245, 245),
        errorContainerColor = Color(245, 245, 245),
        focusedIndicatorColor = Color(70, 130, 180),
        unfocusedIndicatorColor = Color(200, 200, 200),
        disabledIndicatorColor = Color(200, 200, 200),
        errorIndicatorColor = Color.Red,
        focusedLabelColor = Color(70, 130, 180),
        unfocusedLabelColor = Color(150, 150, 150),
        disabledLabelColor = Color(150, 150, 150),
        errorLabelColor = Color.Red
    )
}