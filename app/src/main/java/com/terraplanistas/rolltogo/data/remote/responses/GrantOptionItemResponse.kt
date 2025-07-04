package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GrantOptionItemResponse(
    @SerializedName("granterOptionSet")
    val granterOptionSet: GrantOptionSetResponse,

    @SerializedName("grantedContentId")
    val grantedContent: ContentResponse
)