package com.terraplanistas.rolltogo.ui.navigations
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationHomeScreen
import com.terraplanistas.rolltogo.ui.screens.characterScreen.CharacterScreen
import com.terraplanistas.rolltogo.ui.screens.characterScreen.SecondaryScreens.ItemsScreen
import com.terraplanistas.rolltogo.ui.screens.forumScreen.ForumScreen
import com.terraplanistas.rolltogo.ui.screens.login.LoginScreen
import com.terraplanistas.rolltogo.ui.screens.forumScreen.forumSearchScreens.ForumCharacterSearchScreen
import com.terraplanistas.rolltogo.ui.screens.friendsScreen.FriendsScreen
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.composables.icons.lucide.Backpack
import com.composables.icons.lucide.Feather
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.PencilLine
import com.composables.icons.lucide.PersonStanding
import com.composables.icons.lucide.Sparkles
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.layout.bars.BarItem
import com.terraplanistas.rolltogo.ui.layout.bars.BaseBottomBar
import com.terraplanistas.rolltogo.ui.layout.bars.TopGoBackBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar.HomeBottomNavigationBar
import com.terraplanistas.rolltogo.ui.layout.bars.HomeBottomNavigationBar.PlusButton
import com.terraplanistas.rolltogo.ui.screens.campaingCreation.CampaignCreationScreen
import com.terraplanistas.rolltogo.ui.screens.characterScreen.SecondaryScreens.BiographyScreen
import com.terraplanistas.rolltogo.ui.screens.characterScreen.SecondaryScreens.FeatsScreen
import com.terraplanistas.rolltogo.ui.screens.characterScreen.SecondaryScreens.SpellsScreen
import com.terraplanistas.rolltogo.ui.screens.content.ContentTypeSelectionScreen
import com.terraplanistas.rolltogo.ui.screens.content.contentcreation.ContentCreationScreen
import com.terraplanistas.rolltogo.ui.screens.profile.ProfileScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentView by rememberSaveable { mutableStateOf("") }
    var showCampaignModal = rememberSaveable { mutableStateOf(false) }

    val showDropDown = rememberSaveable { mutableStateOf(false) }
    val modifyDropDownState = { newState: Boolean ->
        showDropDown.value = newState
    }

    val characterViews = listOf<String>("actor","actor_items","actor_spells","actor_feats","actor_biography")
    val baseViews = listOf("forum","new_actor","friends","search_characters", "account","content_creation","content_creation_Navigation", "campaigns_list", "campaign_creation")

    Scaffold(
        topBar = {
            if (
                currentView in characterViews
            ) {
                TopGoBackBar(goBack = { navController.popBackStack() })
            }
        },
        bottomBar = {
            if (
                currentView in characterViews
            ) {
                val currentCharacterId = navBackStackEntry?.arguments?.getString("id") ?: "holaf"

                BaseBottomBar(
                    items = listOf(
                        BarItem(
                            text = stringResource(R.string.actor_screen_character),
                            icon = Lucide.PersonStanding,
                            navigation = ActorScreenNavigation(currentCharacterId)
                        ),
                        BarItem(
                            text = stringResource(R.string.actor_screen_inventory),
                            icon = Lucide.Backpack,
                            navigation = ActorItemsListNavigation(currentCharacterId)
                        ),
                        BarItem(
                            text = stringResource(R.string.actor_screen_spells),
                            icon = Lucide.Sparkles,
                            navigation = ActorSpellsListNavigation(currentCharacterId)
                        ),
                        BarItem(
                            text = stringResource(R.string.actor_screen_feats),
                            icon = Lucide.Feather,
                            navigation = ActorFeatsListNavigation(currentCharacterId)
                        ),
                        BarItem(
                            text = stringResource(R.string.actor_screen_biography),
                            icon = Lucide.PencilLine,
                            navigation = ActorBiographyScreenNavigation(currentCharacterId)
                        )
                    ),
                    navController = navController
                )
            }

            if(currentView in baseViews){
                HomeBottomNavigationBar(navController) { }
            }
        },
        floatingActionButton = {
            if (currentView in baseViews
                ){
                PlusButton(
                    size = 78.dp,
                    expanded = showDropDown.value,
                    hide = { modifyDropDownState(false) },
                    navigateToNewCharacter = {navController.navigate(NewActorNavigation)},
                    navigateNewCampaign = {navController.navigate(CampaignCreation)},
                    showDropDown = {modifyDropDownState(true)},
                    navigateToContentCreation = {navController.navigate(ContentCreationNavigation)},
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = LoginScreen,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable<ForumNavigation> {
                currentView = "forum"
                ForumScreen(navController)
            }
            composable<NewActorNavigation> {
                currentView = "new_actor"
                ActorCreationHomeScreen(navController)
            }
            composable<ActorScreenNavigation> { actorScreenArgs ->
                currentView = "actor"
                CharacterScreen(navController, actorScreenArgs.id)
            }
            composable<FriendsNavigation> {
                currentView = "friends"
                FriendsScreen(navController)
            }
            composable<SearchCharactersNavigation> {
                currentView = "search_characters"
                ForumCharacterSearchScreen(navController)
            }
            composable<ActorItemsListNavigation> { actorItemsListArgs ->
                currentView = "actor_items"
                ItemsScreen(id = actorItemsListArgs.id)
            }
            composable<CampaignsNavigation> {
                currentView = "campaigns"
                Text("Campaigns Screen")
            }
            composable<ActorSpellsListNavigation> { actorSpellsListArgs ->
                currentView = "actor_spells"
                SpellsScreen(id = actorSpellsListArgs.id)
            }
            composable<ActorFeatsListNavigation> { actorFeatsListArgs ->
                currentView = "actor_feats"
                FeatsScreen(id = actorFeatsListArgs.id)
            }
            composable<ActorBiographyScreenNavigation> { actorBiographyScreenArgs ->
                currentView = "actor_biography"
                BiographyScreen(id = actorBiographyScreenArgs.id)
            }
            composable<LoginScreen>{
                LoginScreen(nav = navController)
                currentView = "login"
            }
            composable<AccountNavigation> {
                ProfileScreen(nav = navController)
                currentView = "account"
            }
            composable<ContentCreationNavigation> {
                currentView = "content_creation_Navigation"
                ContentTypeSelectionScreen(nav = navController)
            }
            composable<ContentCreation> {  contentCreationArgs ->
                currentView = "content_creation"
                val creatingType = navBackStackEntry?.arguments?.getString("type") ?: "items"
                Log.d("ContentCreationNavigation", "Creating: $creatingType")
                ContentCreationScreen(
                    type = creatingType,
                    nav = navController
                )

            }
            composable<CampaignCreation>{
                CampaignCreationScreen(
                    navController = navController
                )
            }
        }
    }
}
