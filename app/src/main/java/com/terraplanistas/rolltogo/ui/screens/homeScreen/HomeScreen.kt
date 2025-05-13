package com.terraplanistas.rolltogo.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar.HomeBottomNavigationBar
import com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar.PlusButton

@Composable

fun HomeScreen(navController: NavController, floatingAction: () -> Unit) {
    Box {
        Scaffold(
            bottomBar = {
                HomeBottomNavigationBar(navController = navController, floatingAction)
            },
            floatingActionButton = {
                PlusButton(onClick = floatingAction, size = 64.dp)
            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(listOf<Color>(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.surfaceContainer),
                            radius = 1300f
                        ))
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
