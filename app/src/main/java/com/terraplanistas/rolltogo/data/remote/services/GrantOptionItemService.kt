package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.GrantOptionItemCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.GrantOptionItemResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface GrantOptionItemService {

    @GET("grant-option-items")
    suspend fun getAllGrantOptionItems(): List<GrantOptionItemResponse>

    @GET("grant-option-items/{id}")
    suspend fun getGrantOptionItemById(@Path("id") id: UUID): GrantOptionItemResponse

    @POST("grant-option-items")
    suspend fun createGrantOptionItem(@Body request: GrantOptionItemCreateRequest): Response<GrantOptionItemResponse>

    @PUT("grant-option-items")
    suspend fun updateGrantOptionItem(@Body grantOptionItem: GrantOptionItemResponse): GrantOptionItemResponse

    @DELETE("grant-option-items/{id}")
    suspend fun deleteGrantOptionItemById(@Path("id") id: UUID)
}