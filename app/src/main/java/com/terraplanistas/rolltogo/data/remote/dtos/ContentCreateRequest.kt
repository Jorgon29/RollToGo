package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName
import com.terraplanistas.rolltogo.data.enums.SourceContentEnum
import com.terraplanistas.rolltogo.data.enums.VisibilityEnum

data class ContentCreateRequest(
    @SerializedName("sourceContentEnum")
    val sourceContentEnum: SourceContentEnum,

    @SerializedName("visibilityEnum")
    val visibilityEnum: VisibilityEnum,

    @SerializedName("authorId")
    val authorId: String
)
