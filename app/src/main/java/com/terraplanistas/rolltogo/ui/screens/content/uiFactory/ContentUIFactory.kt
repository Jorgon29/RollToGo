package com.terraplanistas.rolltogo.ui.screens.content.uiFactory

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.ui.navigations.ForumNavigation
import com.terraplanistas.rolltogo.ui.screens.content.screens.item.ItemCreationViewModel
import com.terraplanistas.rolltogo.ui.screens.content.screens.background.BackgroundContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.creatures.CreatureContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.features.FeatureCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.item.ItemContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.species.SpecieContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.content.screens.spells.SpellContentCreationScreen

object ContentUIFactory {
    fun createScreen(
        type: SourceContentEnum?,
        viewModel: ItemCreationViewModel,
        nav: NavHostController
    ): @Composable () -> Unit {
        return when (type) {
            SourceContentEnum.ITEM -> {
                { ItemContentCreationScreen( nav = nav) }
            }
            SourceContentEnum.SPELLS -> {
                { SpellContentCreationScreen(nav =  nav) }
            }
            SourceContentEnum.BACKGROUND -> {
                { BackgroundContentCreationScreen( nav = nav) }
            }
            SourceContentEnum.SPECIES -> {
                { SpecieContentCreationScreen( nav =  nav) }
            }
            SourceContentEnum.CREATURES -> {
                { CreatureContentCreationScreen( nav =  nav) }
            }
            SourceContentEnum.FEATURES ->{
                { FeatureCreationScreen(nav = nav) }
            }
            else -> {
                { nav.navigate(ForumNavigation)}
            }

        }

    }


}