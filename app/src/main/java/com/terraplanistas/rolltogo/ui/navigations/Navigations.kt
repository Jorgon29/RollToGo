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
data class ActorScreenNavigation(val id: String)

@Serializable
data class ActorItemsListNavigation(val id: String)

@Serializable
data class ActorSpellsListNavigation(val id: String)

@Serializable
data class ActorFeatsListNavigation(val id: String)

@Serializable
object LoginScreen

@Serializable
data class ActorBiographyScreenNavigation(val id: String)

@Serializable
object SearchCharactersNavigation

@Serializable
object ContentCreationNavigation

@Serializable
data class ContentCreation(val type: String)

@Serializable
object CampaingsList

@Serializable
object CampaignCreation

@Serializable
data class CampaingNavigation(val id: String)

@Serializable
object CampaignListNavigation

@Serializable
object CampaignCreationNavigation

@Serializable
object FeatureList

@Serializable
object BackgroundList


