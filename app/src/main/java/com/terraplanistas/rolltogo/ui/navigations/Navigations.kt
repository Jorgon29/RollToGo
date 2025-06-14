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
object NewCampaignNavigation

@Serializable
data class ActorScreenNavigation(val id: Int)

@Serializable
object LoginScreen