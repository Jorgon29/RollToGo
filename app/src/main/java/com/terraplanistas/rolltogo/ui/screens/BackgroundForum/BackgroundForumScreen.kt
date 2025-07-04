package com.terraplanistas.rolltogo.ui.screens.BackgroundForum

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BackgroundForumScreen(
    viewModel: BackgroundForumViewModel = viewModel(factory = BackgroundForumViewModel.factory),
) {
    val backgrounds by viewModel.backgrounds.collectAsState()
    val showDeleteDialog by viewModel.showDeleteDialog.collectAsState()
    val selectedBackground by viewModel.selectedBackground.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val showEditor by viewModel.showEditDialog.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(72, 94, 146))) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (backgrounds.isEmpty()) {
            Text(
                "No backgrounds available",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(backgrounds) { background ->
                    BackgroundItem(
                        background = background,
                        onEditClick = { viewModel.selectBackgroundForEdit(it) },
                        onDeleteClick = { viewModel.selectBackgroundForDelete(it) }
                    )
                }
            }
        }

        // Diálogo de confirmación para borrar
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissDeleteDialog() },
                title = { Text("Delete background") },
                text = { Text("Are you sure you want to delete this background?") },
                confirmButton = {
                    Button(
                        onClick = { viewModel.deleteBackground() },
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

        selectedBackground?.let { background ->
            var name by remember { mutableStateOf(background.background.name) }
            var description by remember { mutableStateOf(background.background.description ?: "") }

            if (showEditor) {
                AlertDialog(
                    onDismissRequest = { viewModel.dismissEditDialog() },
                    title = { Text("Edit Background") },
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
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                val updated = background.copy(
                                    background = background.background.copy(
                                        name = name,
                                        description = description
                                    )
                                )
                                viewModel.updateBackground(updated)
                                viewModel.dismissEditDialog()
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
}

@Composable
private fun BackgroundItem(
    background: BackgroundWithContent,
    onEditClick: (BackgroundWithContent) -> Unit,
    onDeleteClick: (BackgroundWithContent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Nombre del background
            Text(
                text = background.background.name,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción
            Text(
                text = background.background.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // Botón de editar
                IconButton(
                    onClick = { onEditClick(background) },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botón de eliminar
                IconButton(
                    onClick = { onDeleteClick(background) },
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}