package com.terraplanistas.rolltogo.ui.layout.bars

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController

@Composable
fun BaseBottomBar(items: List<BarItem>, navController: NavController){
    val selected = rememberSaveable() { mutableStateOf(items[0].text) }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item.text)},
                icon = { Icon(item.icon, item.text)},
                selected = selected.value == item.text,
                onClick = {
                    selected.value = item.text
                    // navController.navigate(item.navigation)
                }

            )
        }
    }
}