package com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.terraplanistas.rolltogo.R
import kotlinx.coroutines.launch

@Composable
fun SelectionScreen(
    items: List<SelectionListItem>,
    snackbarHostState: SnackbarHostState,
    setSelected: (Int) -> Unit,
    selected: Int
) {
    val coroutineScope = rememberCoroutineScope()
    val selectedText = stringResource(R.string.selected)

    SelectionList(
        items = items,
        showSnackBar = { message: String ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar("$selectedText: $message")
            }
        },
        setSelected = setSelected,
        selected = selected
    )
}
