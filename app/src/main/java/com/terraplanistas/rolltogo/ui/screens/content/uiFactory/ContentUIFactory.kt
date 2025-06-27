package com.terraplanistas.rolltogo.ui.screens.content.uiFactory

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.ui.navigations.ForumNavigation
import com.terraplanistas.rolltogo.ui.screens.content.ContentCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.BackgroundContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.creatures.CreatureContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.item.ItemContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.species.SpecieContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.spells.SpellContentCreationScreen

object ContentUIFactory {
    fun createScreen(
        type: SourceContentEnum,
        viewModel: ContentCreationViewModel,
        nav : NavHostController
    ): @Composable () -> Unit {
        return when (type) {
            SourceContentEnum.ITEM -> {
                { ItemContentCreationScreen(viewModel, nav) }
            }
            SourceContentEnum.SPELLS -> {
                { SpellContentCreationScreen(viewModel, nav) }
            }
            SourceContentEnum.BACKGROUND -> {
                { BackgroundContentCreationScreen(viewModel, nav) }
            }
            SourceContentEnum.SPECIES -> {
                { SpecieContentCreationScreen(viewModel, nav) }
            }

            SourceContentEnum.CREATURES -> {
                { CreatureContentCreationScreen(viewModel, nav) }
            }
            else -> {
                { nav.navigate(ForumNavigation)}
            }

        }

    }


}