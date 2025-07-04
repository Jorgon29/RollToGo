package com.terraplanistas.rolltogo.ui.layout.bars.RoomBar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.composables.icons.lucide.BookOpen
import com.composables.icons.lucide.BookOpenText
import com.composables.icons.lucide.CircleUser
import com.composables.icons.lucide.CircleUserRound
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MessagesSquare
import com.composables.icons.lucide.UserRoundPlus
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.repository.settings.UserPreferencesRepository
import com.terraplanistas.rolltogo.ui.layout.bars.BarItem
import com.terraplanistas.rolltogo.ui.layout.bars.BaseBottomBar
import com.terraplanistas.rolltogo.ui.navigations.CampaignCharacterSelectionNavigation
import com.terraplanistas.rolltogo.ui.screens.campaignDetails.CampaignChatScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RoomBottomNavigationBar(navController: NavController, roomBottomBarViewModel: RoomBottomBarViewModel = viewModel(factory = RoomBottomBarViewModel.factory)){
    val items = listOf(
        BarItem(
            text = stringResource(R.string.room_chat),
            icon = Lucide.BookOpen,
            navigation = CampaignChatScreen(
                roomId = roomBottomBarViewModel.getCurrentRoomId(),
                title = roomBottomBarViewModel.getCurrentRoomTitle()
            ),

            ),
        BarItem(
            text = stringResource(R.string.room_character_selection),
            icon = Lucide.CircleUser,
            navigation = CampaignCharacterSelectionNavigation
        )
    )

    BaseBottomBar(navController = navController, items = items)


}