package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.ItemCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.ItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface ItemService {

    @GET("items")
    suspend fun getAllItems(): List<ItemResponse>

    @GET("items/{id}")
    suspend fun getItemById(@Path("id") id: UUID): ItemResponse?

    @POST("items")
    suspend fun createItem(@Body request: ItemCreateRequest): Response<ItemResponse>

    @PUT("items")
    suspend fun updateItem(@Body item: ItemResponse): ItemResponse

    @DELETE("items/{id}")
    suspend fun deleteItemById(@Path("id") id: UUID)
}