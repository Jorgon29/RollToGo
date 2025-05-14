package com.terraplanistas.rolltogo.ui.screens.forumScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox
import com.terraplanistas.rolltogo.ui.screens.baseHomeScreen.BaseHomeScreen

@Composable
fun ForumScreen(navController: NavController) {
    BaseHomeScreen(navController, title = stringResource(R.string.bottom_navigation_forum),
        content = {
            CategoryBox(title = "Personajes")
            Spacer(modifier = Modifier.height(16.dp))
            CategoryBox(title = "Monstruos")
        })
}