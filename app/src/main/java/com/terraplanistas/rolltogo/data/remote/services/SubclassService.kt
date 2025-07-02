package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SubclassCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SubclassResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SubclassService {

    @GET("subclasses")
    suspend fun getAllSubclasses(): List<SubclassResponse>

    @GET("subclasses/{id}")
    suspend fun getSubclassById(@Path("id") id: UUID): SubclassResponse

    @POST("subclasses")
    suspend fun createSubclass(@Body request: SubclassCreateRequest): Response<SubclassResponse>
    @PUT("subclasses")
    suspend fun updateSubclass(@Body subclass: SubclassResponse): SubclassResponse

    @DELETE("subclasses/{id}")
    suspend fun deleteSubclassById(@Path("id") id: UUID)
}