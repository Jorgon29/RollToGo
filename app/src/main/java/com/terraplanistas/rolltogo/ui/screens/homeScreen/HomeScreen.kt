package com.terraplanistas.rolltogo.ui.screens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar

@Composable

fun HomeScreen(navController: NavController, floatingAction: () -> Unit) {
    Box {
        Scaffold(
            bottomBar = {
                HomeBottomNavigationBar(navController = navController, floatingAction)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = floatingAction,
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(imageVector = Lucide.Plus, contentDescription = "Add")
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text("Quesitrix")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController(), {})
}
