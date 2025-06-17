package com.terraplanistas.rolltogo.ui.navigations
import kotlinx.serialization.Serializable

@Serializable
object ForumNavigation {
    const val ROUTE_NAME = "ForumNavigation"
}

@Serializable
object FriendsNavigation {
    const val ROUTE_NAME = "FriendsNavigation"
}

@Serializable
object CampaignsNavigation {
    const val ROUTE_NAME = "CampaignsNavigation"
}

@Serializable
object AccountNavigation {
    const val ROUTE_NAME = "AccountNavigation"
}

@Serializable
object NewActorNavigation {
    const val ROUTE_NAME = "NewActorNavigation"
}

@Serializable
data class ActorScreenNavigation(val id: String) {
    val BASE_ROUTE_NAME = "ActorScreenNavigation"
}

@Serializable
data class ActorItemsListNavigation(val id: String) {
    val BASE_ROUTE_NAME = "ActorItemsListNavigation"
}

@Serializable
data class ActorSpellsListNavigation(val id: String) {
    val BASE_ROUTE_NAME = "ActorSpellsListNavigation"
}

@Serializable
data class ActorFeatsListNavigation(val id: String) {
    val BASE_ROUTE_NAME = "ActorFeatsListNavigation"
}

@Serializable
data class ActorBiographyScreenNavigation(val id: String) {
    val BASE_ROUTE_NAME = "ActorBiographyScreenNavigation"
}

@Serializable
object SearchCharactersNavigation {
    val ROUTE_NAME = "SearchCharactersNavigation"
}