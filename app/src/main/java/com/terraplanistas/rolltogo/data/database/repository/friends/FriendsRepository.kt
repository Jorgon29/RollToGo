package com.terraplanistas.rolltogo.data.database.repository.friends

import com.terraplanistas.rolltogo.data.model.Friend
import kotlinx.coroutines.flow.Flow

interface FriendsRepository {
    fun getFriends(): Flow<List<Friend>>
    suspend fun addFriend(friend: Friend)
    suspend fun removeFriend(friend: Friend)
}