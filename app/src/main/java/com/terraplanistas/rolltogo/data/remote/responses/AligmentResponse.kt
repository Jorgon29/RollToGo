package com.terraplanistas.rolltogo.data.remote.services


data class AlignmentResponse(
    val index: String,
    val name: String,
    val abbreviation: String,
    val desc: String,
    val url: String
)


data class ApiListResponse<T>(
    val count: Int,
    val results: List<T>
)