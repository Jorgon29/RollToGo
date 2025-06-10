package com.terraplanistas.rolltogo.data.database.repository.friends

import com.terraplanistas.rolltogo.data.database.dao.FriendDao
import com.terraplanistas.rolltogo.data.database.entities.toDomain
import com.terraplanistas.rolltogo.data.model.Friend
import com.terraplanistas.rolltogo.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FriendsRepositoryImplementation(
    private val friendDao: FriendDao
): FriendsRepository {

    override fun getFriends(): Flow<List<Friend>> {
        return friendDao.getFriends().map { it.map {
            it.toDomain()
        }}
    }

    override suspend fun addFriend(friend: Friend) {
        friendDao.addFriend(friend.toEntity())
    }

    override suspend fun removeFriend(friend: Friend) {
        friendDao.removeFriend(friend.toEntity())
    }

}