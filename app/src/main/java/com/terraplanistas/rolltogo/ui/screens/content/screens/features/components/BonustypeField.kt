package com.terraplanistas.rolltogo.ui.screens.content.screens.features.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.terraplanistas.rolltogo.data.enums.BonusTypeEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BonustypeField(
    modifier: Modifier = Modifier,
    bonusType: String,
    onBonusTypeChanged: (String) -> Unit,

    ) {

    val bonusTypes = BonusTypeEnum.entries.map { it.value }

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = bonusType,
                onValueChange = {},
                label = { Text("Tipo de bonus") },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
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
                bonusTypes.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            onBonusTypeChanged(type)
                            expanded = false
                        },
                        colors = MenuItemColors(
                            textColor = Color.White,
                            leadingIconColor = Color.White,
                            trailingIconColor = Color(255, 255, 255),
                            disabledTextColor = Color(255, 255, 255),
                            disabledLeadingIconColor = Color(255, 255, 255),
                            disabledTrailingIconColor = Color(
                                255,
                                255,
                                255
                            ),
                        )
                    )
                }
            }

        }
    }


}