package com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SelectionList(
    items: List<SelectionListItem>,
    showSnackBar: (String) -> Unit,
    setSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    selected: Int
) {
    LazyColumn(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        itemsIndexed(items) { index, item ->
            SelectionListCard(item, showSnackBar, setSelected, isSelected = selected == item.id)
        }
    }
}
