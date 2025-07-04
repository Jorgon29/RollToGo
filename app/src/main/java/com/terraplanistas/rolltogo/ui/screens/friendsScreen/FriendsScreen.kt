package com.terraplanistas.rolltogo.ui.screens.friendsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Plus
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.Friend
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox
import com.terraplanistas.rolltogo.ui.screens.baseHomeScreen.BaseHomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsScreen(navController: NavController, viewModel: FriendsScreenViewModel = viewModel(factory = FriendsScreenViewModel.Factory)){

    val friends = emptyList<Friend>()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheetModal by rememberSaveable { mutableStateOf(false) }
    var selectedFriendName = rememberSaveable { mutableStateOf("Chepeton15") }
    var selectedFriendId = rememberSaveable { mutableStateOf(0) }
    var searchUserValue = rememberSaveable { mutableStateOf("") }
    var sendRequest = rememberSaveable { mutableStateOf(false) }

    BaseHomeScreen(
        navController = navController,
        title = stringResource(R.string.friends),
        content = {
            LazyColumn { 
                items(friends) { friend: Friend ->
                    CategoryBox(
                        title = friend.name,
                        onClick = {
                            selectedFriendName.value = friend.name
                            selectedFriendId.value = friend.id
                            showBottomSheetModal = true
                            sendRequest.value = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.add_friend))
                IconButton(
                    onClick = {
                        showBottomSheetModal = true
                        sendRequest.value = true
                    }
                ) {
                    Image(
                        imageVector = Lucide.Plus,
                        stringResource(R.string.add_friend)
                    )
                }
            }

            
            if(showBottomSheetModal){
                ModalBottomSheet(
                    onDismissRequest = {showBottomSheetModal=false;sendRequest.value=true},
                    sheetState = sheetState,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if(!sendRequest.value){
                            Text(selectedFriendName.value)
                            HorizontalDivider()
                            Button(
                                onClick = {}
                            ) {
                                Text(stringResource(R.string.block))
                            }
                            Spacer(Modifier.height(16.dp))
                            Button(
                                onClick = {}
                            ) {
                                Text(stringResource(R.string.delete))
                            }
                        } else {
                            Text(stringResource(R.string.add_friend))
                            Spacer(modifier = Modifier.height(16.dp))
                            HorizontalDivider()
                            Spacer(modifier = Modifier.height(16.dp))
                            TextField(
                                value = searchUserValue.value,
                                onValueChange = {searchUserValue.value = it},
                                placeholder = {Text(stringResource(R.string.username))}
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Button(
                                    onClick = {
                                        showBottomSheetModal = false
                                        sendRequest.value = false
                                    }
                                ) {
                                    Text(stringResource(R.string.cancel))
                                }

                                Button(
                                    onClick = {//algo
                                         }
                                ) {
                                    Text(stringResource(R.string.send_friend_request))
                                }
                            }
                        }

                    }
                }
            }
        }
    )
}