package com.terraplanistas.rolltogo.ui.screens.featuresForum

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.components.FeatureItem
import kotlin.let

@Composable
fun FeatureForumScreen(
    viewModel: FeatureForumScreenViewModel = viewModel(factory = FeatureForumScreenViewModel.factory),
) {
    val features by viewModel.features.collectAsState()
    val showDeleteDialog by viewModel.showDeleteDialog.collectAsState()
    val selectedFeature by viewModel.selectedFeature.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (features.isEmpty()) {
            Text(
                "No features available",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(features) { feature ->
                    FeatureItem(
                        feature = feature,
                        onEditClick = { viewModel.selectFeatureForEdit(it) },
                        onDeleteClick = { viewModel.selectFeatureForDelete(it) }
                    )
                }
            }
        }


        // Diálogo de confirmación para borrar
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissDeleteDialog() }, // Usamos función del VM
                title = { Text("Delete Feature") },
                text = { Text("Are you sure you want to delete this feature?") },
                confirmButton = {
                    Button(
                        onClick = { viewModel.deleteFeature() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    Button(onClick = { viewModel.dismissDeleteDialog() }) {
                        Text("Cancel")
                    }
                }
            )
        }

        // Diálogo/Formulario de edición
        selectedFeature?.let { feature ->
            var name by remember { mutableStateOf(feature.feature.name) }
            var description by remember { mutableStateOf(feature.feature.description ?: "") }
            var isMagic by remember { mutableStateOf(feature.feature.is_magical) }
            var isPassive by remember { mutableStateOf(feature.feature.is_passive) }

            AlertDialog(
                onDismissRequest = { viewModel.dismissEditDialog() }, // Usamos función del VM
                title = { Text("Edit Feature") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Description") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isMagic,
                                onCheckedChange = { isMagic = it }
                            )
                            Text("Is Magical", modifier = Modifier.padding(start = 8.dp))
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isPassive,
                                onCheckedChange = { isPassive = it }
                            )
                            Text("Is Passive", modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            val updated = feature.copy(
                                feature = feature.feature.copy(
                                    name = name,
                                    description = description,
                                    is_magical = isMagic,
                                    is_passive = isPassive
                                )
                            )
                            viewModel.updateFeature(updated)
                            viewModel.dismissEditDialog() // Usamos función del VM
                        }
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = { viewModel.dismissEditDialog() }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
private fun FeatureItem(
    feature: FeatureWithContent,
    onEditClick: (FeatureWithContent) -> Unit,
    onDeleteClick: (FeatureWithContent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Nombre del feature
            Text(
                text = feature.feature.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción
            Text(
                text = feature.feature.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tags de características
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tag para indicar si es mágico
                Box(
                    modifier = Modifier
                        .background(
                            color = if (feature.feature.is_magical) Color(0xFFBB86FC) else Color(0xFF03DAC6),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (feature.feature.is_magical) "Mágico" else "No mágico",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }

                // Tag para indicar si es pasivo
                Box(
                    modifier = Modifier
                        .background(
                            color = if (feature.feature.is_passive) Color(0xFFCF6679) else Color(0xFF6200EE),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (feature.feature.is_passive) "Pasivo" else "Activo",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // Botón de editar
                IconButton(
                    onClick = { onEditClick(feature) },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botón de eliminar
                IconButton(
                    onClick = { onDeleteClick(feature) },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}