package com.terraplanistas.rolltogo.ui.navigations

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationHomeScreen
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.forumScreen.ForumScreen

@Composable
fun NavigationHost(){
    val navController = rememberNavController()

    Column {
        NavHost(navController = navController, startDestination = ForumNavigation){
            composable<ForumNavigation> {
                ForumScreen(navController)
            }

            composable<NewActorNavigation> {
                ActorCreationHomeScreen(navController)
            }
        }
    }
}