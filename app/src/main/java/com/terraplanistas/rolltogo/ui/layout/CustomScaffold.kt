package com.terraplanistas.rolltogo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.terraplanistas.rolltogo.ui.navigations.NavigationHost

@Composable
fun CustomScaffold(){
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavigationHost()
        }
    }
}