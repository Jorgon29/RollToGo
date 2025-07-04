package com.terraplanistas.rolltogo.data.remote.services

data class ApiListResponse<T>(
    val count: Int,
    val results: List<T>
)
