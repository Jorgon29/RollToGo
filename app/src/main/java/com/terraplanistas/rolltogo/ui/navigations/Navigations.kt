package com.terraplanistas.rolltogo.ui.navigations

import kotlinx.serialization.Serializable

@Serializable
object ForumNavigation

@Serializable
object FriendsNavigation

@Serializable
object CampaignsNavigation

@Serializable
object AccountNavigation

@Serializable
object NewActorNavigation

@Serializable
data class ActorScreenNavigation(val id: Int)

@Serializable
object SearchCharactersNavigation