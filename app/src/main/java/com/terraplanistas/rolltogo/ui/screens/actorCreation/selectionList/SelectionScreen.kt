package com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.R
import kotlinx.coroutines.launch

@Composable
fun SelectionScreen(items: List<SelectionListItem>) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var selectedId by remember { mutableStateOf(-1) }
    val selectedText = stringResource(R.string.selected)

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        SelectionList(
            items = items,
            showSnackBar = { message: String ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("$selectedText: $message")
                }
            },
            setSelected = { id: Int -> selectedId = id },
        )
    }

}
