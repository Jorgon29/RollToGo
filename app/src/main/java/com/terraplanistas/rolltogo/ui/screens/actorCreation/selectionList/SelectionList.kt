package com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable

@Composable
fun SelectionList(items: List<SelectionListItem>){
    LazyColumn {
        itemsIndexed(items) { index, item ->
            SelectionListCard(item)
        }
    }
}