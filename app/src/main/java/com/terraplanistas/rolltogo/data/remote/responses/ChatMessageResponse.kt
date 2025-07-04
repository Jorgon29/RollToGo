package com.terraplanistas.rolltogo.data.remote.responses



data class ChatMessageResponse(
    val id: String,
    val roomId: String,
    val senderId: String,
    val sender: String,
    val content: String,
    val createdAt: String
)