package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum
import java.time.OffsetDateTime

data class ContentResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("sourceContentEnum")
    val sourceContentEnum: SourceContentEnum,

    @SerializedName("visibilityEnum")
    val visibilityEnum: VisibilityEnum,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("author")
    val author: UserResponse,

    @SerializedName("grants")
    val grants: List<GrantResponse>?,

    @SerializedName("grantedBy")
    val grantedBy: List<GrantResponse>?,

    @SerializedName("grantOptionSets")
    val grantOptionSets: List<GrantOptionSetResponse>?,

    @SerializedName("grantOptionItems")
    val grantOptionItems: List<GrantOptionItemResponse>?
)