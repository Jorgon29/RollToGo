package com.terraplanistas.rolltogo.data.remote.dtos

data class ChatMessageRequest(
    val roomId: String,
    val sender: String,
    val content: String
)