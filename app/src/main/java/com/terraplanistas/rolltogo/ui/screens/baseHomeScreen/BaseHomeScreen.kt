package com.terraplanistas.rolltogo.ui.screens.baseHomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar.HomeBottomNavigationBar
import com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar.PlusButton
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle
import com.terraplanistas.rolltogo.ui.navigations.NewActorNavigation
import kotlin.math.exp


@Composable

fun BaseHomeScreen(navController: NavController, title: String? = null, content: @Composable () -> Unit) {
    val showDropDown = rememberSaveable { mutableStateOf(false) }
    val floatingAction: () -> Unit = {

    }
    val modifyDropDownState = { newState: Boolean ->
        showDropDown.value = newState
    }

    Box {
        Scaffold(
            bottomBar = {
                HomeBottomNavigationBar(navController = navController, {floatingAction()})
            },
            floatingActionButton = {
                PlusButton(
                    size = 88.dp,
                    expanded = showDropDown.value,
                    hide = { modifyDropDownState(false) },
                    navigateToNewCharacter = {navController.navigate(NewActorNavigation)},
                    navigateNewCampaign = {navController.navigate(NewActorNavigation)},
                    showDropDown = {modifyDropDownState(true)}
                )
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
                        )),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let{
                    BasicTitle(title)
                }
                content()
            }

        }
    }
}

