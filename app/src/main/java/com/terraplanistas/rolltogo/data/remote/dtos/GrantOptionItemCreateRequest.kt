package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class GrantOptionItemCreateRequest(
    @SerializedName("granterOptionSetId")
    val granterOptionSetId: String,

    @SerializedName("grantedContentId")
    val grantedContentId: String
)