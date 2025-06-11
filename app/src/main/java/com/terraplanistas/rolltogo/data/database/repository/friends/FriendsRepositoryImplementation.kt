package com.terraplanistas.rolltogo.data.database.repository.friends

import com.terraplanistas.rolltogo.data.database.dao.FriendDao
import com.terraplanistas.rolltogo.data.database.entities.toDomain
import com.terraplanistas.rolltogo.data.database.repository.BaseRepository
import com.terraplanistas.rolltogo.data.model.Friend
import com.terraplanistas.rolltogo.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FriendsRepositoryImplementation(
    private val friendDao: FriendDao
): BaseRepository<Friend> {
    override fun getElements(): Flow<List<Friend>> {
        return friendDao.getFriends().map { it.map { it.toDomain() } }
    }

    override suspend fun addElement(t: Friend) {
        friendDao.addFriend(t.toEntity())
    }

    override suspend fun addElements(ts: List<Friend>) {
        friendDao.addFriends(ts.map { it.toEntity() })
    }

    override suspend fun removeElement(t: Friend) {
        friendDao.removeFriend(t.toEntity())
    }


}