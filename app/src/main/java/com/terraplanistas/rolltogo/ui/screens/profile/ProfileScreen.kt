package com.terraplanistas.rolltogo.ui.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.ui.screens.baseHomeScreen.BaseHomeScreen

@Composable
fun ProfileScreen(
    vm: ProfielScreenViewmodel = viewModel(factory = ProfielScreenViewmodel.factory),
    nav: NavHostController
) {
    BaseHomeScreen(
        navController = nav,
        title = "Perfil",
        content = {
            Text(text = "Perfil de usuario")
            },
    )

    }