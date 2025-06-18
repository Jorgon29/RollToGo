package com.terraplanistas.rolltogo.data.model.profile

data class Users(
    val id: Int,
    val profileName: String,
    val profileImgURL: String,
    val age: Int,
    val email: String,
    val friends: List<Users>
)
