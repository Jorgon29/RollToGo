package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.ItemTagCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.ItemTagResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface ItemTagService {

    @GET("item-tags")
    suspend fun getAllItemTags(): List<ItemTagResponse>

    @GET("item-tags/{id}")
    suspend fun getItemTagById(@Path("id") id: UUID): ItemTagResponse

    @POST("item-tags")
    suspend fun createItemTag(@Body request: ItemTagCreateRequest): Response<ItemTagResponse>

    @PUT("item-tags")
    suspend fun updateItemTag(@Body itemTag: ItemTagResponse): ItemTagResponse

    @DELETE("item-tags/{id}")
    suspend fun deleteItemTagById(@Path("id") id: UUID)
}