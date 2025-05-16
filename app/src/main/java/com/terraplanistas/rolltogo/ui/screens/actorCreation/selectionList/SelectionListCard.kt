package com.terraplanistas.rolltogo.ui.screens.actorCreation.selectionList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectionListCard(item: SelectionListItem){
    Row {
        Image(
            item.icon,
            item.name
        )
        Spacer(
            Modifier.width(16.dp)
        )
        Column {
            Text(
                text = item.name,
                fontSize = 32.sp
            )
            item.description?.let {
                Spacer(
                    Modifier.height(12.dp)
                )
                Text(
                    text = item.description
                )
            }


        }
    }
}