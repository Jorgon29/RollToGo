package com.terraplanistas.rolltogo.ui.screens.content.contentcreation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.ui.screens.content.screens.item.ItemCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.content.uiFactory.ContentUIFactory


@Composable
fun ContentCreationScreen(
    type: String,
    vm: ItemCreationViewModel = viewModel(factory = ItemCreationViewModel.factory),
    nav: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        ContentUIFactory.createScreen(type = SourceContentEnum.fromValue(type), viewModel = vm, nav = nav)()
    }
      Log.d("ContentCreationScreen", "Creating content of type: $type")
    }