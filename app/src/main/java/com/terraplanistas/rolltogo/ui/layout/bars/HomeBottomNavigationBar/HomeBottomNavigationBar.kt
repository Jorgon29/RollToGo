package com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.composables.icons.lucide.BookOpenText
import com.composables.icons.lucide.CircleUserRound
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MessagesSquare
import com.composables.icons.lucide.UserRoundPlus
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.layout.bars.BarItem
import com.terraplanistas.rolltogo.ui.layout.bars.BaseBottomBar
import com.terraplanistas.rolltogo.ui.navigations.AccountNavigation
import com.terraplanistas.rolltogo.ui.navigations.CampaignsNavigation
import com.terraplanistas.rolltogo.ui.navigations.ForumNavigation
import com.terraplanistas.rolltogo.ui.navigations.FriendsNavigation

@Composable
fun HomeBottomNavigationBar(navController: NavController, floatingAction: () -> Unit){
    val items = listOf(
        BarItem(
            text = stringResource(R.string.bottom_navigation_forum),
            icon = Lucide.MessagesSquare,
            navigation = ForumNavigation
        ),
        BarItem(
            text = stringResource(R.string.bottom_navigation_friends),
            icon = Lucide.UserRoundPlus,
            navigation = FriendsNavigation
        ),
        BarItem(
            text = stringResource(R.string.bottom_navigation_campaigns),
            icon = Lucide.BookOpenText,
            navigation = CampaignsNavigation
        ),
        BarItem(
            text = stringResource(R.string.bottom_navigation_account),
            icon = Lucide.CircleUserRound,
            navigation = AccountNavigation
        )
    )

    BaseBottomBar(navController = navController, items = items)
}

@Preview(showBackground = true)
@Composable
fun HomeBottomNavigationBarPreview(){
    HomeBottomNavigationBar(rememberNavController(), {})
}