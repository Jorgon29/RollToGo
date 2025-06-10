package com.terraplanistas.rolltogo.ui.screens.friendsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
import com.terraplanistas.rolltogo.data.database.repository.friends.FriendsRepository
import com.terraplanistas.rolltogo.data.model.Friend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FriendsScreenViewModel(
    private val friendsRepository: FriendsRepository
): ViewModel() {

    fun getFriends(): StateFlow<List<Friend>>{
        return friendsRepository.getFriends().stateIn(
            scope = viewModelScope,
            initialValue = emptyList<Friend>(),
            started = SharingStarted.WhileSubscribed(5000)
        )
    }

    suspend fun addFriend(friend: Friend){
        friendsRepository.addFriend(friend)
    }

    suspend fun removeFriend(friend: Friend){
        friendsRepository.removeFriend(friend)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as RollToGoApp
                FriendsScreenViewModel(
                    aplication.appProvider.provideFriendsRepository()
                )
            }
        }
    }
}