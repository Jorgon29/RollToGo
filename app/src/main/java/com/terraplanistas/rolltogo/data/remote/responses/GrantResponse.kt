package com.terraplanistas.rolltogo.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum

data class GrantResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("granterType")
    val granterType: SourceContentEnum,

    @SerializedName("granterContent")
    val granterContent: ContentResponse,

    @SerializedName("grantedType")
    val grantedType: SourceContentEnum,

    @SerializedName("grantedContent")
    val grantedContent: ContentResponse
)