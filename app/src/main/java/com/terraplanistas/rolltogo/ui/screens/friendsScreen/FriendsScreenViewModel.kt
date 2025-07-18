package com.terraplanistas.rolltogo.ui.screens.friendsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.terraplanistas.rolltogo.RollToGoApp
class FriendsScreenViewModel(
    private val friendsRepository: Any
): ViewModel() {
/*
    fun getFriends(): StateFlow<List<Friend>>{
        return emptyList<Friend>().stateIn(
            scope = viewModelScope,
            initialValue = emptyList<Friend>(),
            started = SharingStarted.WhileSubscribed(5000)
        )
    }

    suspend fun addFriend(friend: Friend){
        friendsRepository.addElement(friend)
    }

    suspend fun removeFriend(friend: Friend){
        friendsRepository.removeElement(friend)
    }*/

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as RollToGoApp
                FriendsScreenViewModel(
                    ""
                )
            }
        }
    }
}