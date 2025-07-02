package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum

data class GrantCreateRequest(
    @SerializedName("granterType")
    val granterType: SourceContentEnum,

    @SerializedName("granterContentId")
    val granterContentId: String,

    @SerializedName("grantedType")
    val grantedType: SourceContentEnum,

    @SerializedName("grantedContentId")
    val grantedContentId: String
)