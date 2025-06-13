@file:OptIn(ExperimentalMaterial3Api::class)

package com.terraplanistas.rolltogo.ui.screens.forumScreen.forumSearchScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.character.DomainCharacter
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep.BiographyInputBox
import com.terraplanistas.rolltogo.ui.screens.baseHomeScreen.BaseHomeScreen

@Composable
fun ForumCharacterSearchScreen(
    navController: NavController,
    viewModel: ForumCharacterSearchViewModel = viewModel(factory = ForumCharacterSearchViewModel.Factory)
){
    BaseHomeScreen(
        navController = navController,
        title = stringResource(R.string.characters),

        content = {
            var searchInput = rememberSaveable { mutableStateOf("") }
            var foundCharacters = viewModel.searchedItems.collectAsState()
            val sheetState = rememberModalBottomSheetState()
            var showModal = rememberSaveable { mutableStateOf(false) }
            var selectedCharacterName = rememberSaveable { mutableStateOf("") }
            var selectedCharacterId = rememberSaveable { mutableStateOf("") }

            LaunchedEffect(searchInput.value) {
                viewModel.searchByName(searchInput.value)
            }

            SearchBar(
                inputField = {
                    BiographyInputBox(
                        placeholder = stringResource(R.string.characters),
                        text = searchInput.value,
                        changeText = {searchInput.value = it}
                    )
                },
                expanded = false,
                onExpandedChange = {},
                modifier = Modifier.fillMaxWidth(0.7f)
            ){

            }

            LazyColumn {
                items(foundCharacters.value) { character: DomainCharacter ->
                    CategoryBox(
                        title = character.name,
                        content = "${character.characterClass} - ${character.race} - lvl. + ${character.level}",
                        onClick = {
                            selectedCharacterId.value = character.id
                            selectedCharacterName.value = character.name
                        }
                    )
                }
            }
            
            if(showModal.value){
                ModalBottomSheet(
                    onDismissRequest = {showModal.value = false},
                    sheetState = sheetState
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(Modifier.height(16.dp))
                        Text(selectedCharacterName.value)
                        Spacer(Modifier.height(16.dp))
                        HorizontalDivider()
                        Button(
                            onClick = {
                            }
                        ) {
                            Text(stringResource(R.string.add))
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = {
                            }
                        ) {
                            Text(stringResource(R.string.see))
                        }
                        Spacer(Modifier.height(96.dp))
                    }
                }
            }

        }
    )
}