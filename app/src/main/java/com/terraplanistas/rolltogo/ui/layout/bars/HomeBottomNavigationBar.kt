package com.terraplanistas.rolltogo.ui.layout.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.composables.icons.lucide.BookOpenText
import com.composables.icons.lucide.CircleUserRound
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MessagesSquare
import com.composables.icons.lucide.Plus
import com.composables.icons.lucide.UserRoundPlus
import com.terraplanistas.rolltogo.R
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

    val selected = rememberSaveable() { mutableStateOf(items[0].text) }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item.text)},
                icon = { Icon(item.icon, item.text)},
                selected = selected.value == item.text,
                onClick = {
                   selected.value = item.text
                   // navController.navigate(item.navigation)
                }

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBottomNavigationBarPreview(){
    HomeBottomNavigationBar(rememberNavController(), {})
}