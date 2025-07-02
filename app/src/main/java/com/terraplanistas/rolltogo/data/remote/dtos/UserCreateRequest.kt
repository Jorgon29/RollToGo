package com.terraplanistas.rolltogo.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class UserCreateRequest(
    @SerializedName("id")
    val id: String,

    @SerializedName("userImageUrl")
    val userImageUrl: String?,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String
)
