package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SubspeciesCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SubspeciesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SubspeciesService {

    @GET("subspecies")
    suspend fun getAllSubspecies(): List<SubspeciesResponse>

    @GET("subspecies/{id}")
    suspend fun getSubspeciesById(@Path("id") id: UUID): SubspeciesResponse

    @POST("subspecies")
    suspend fun createSubspecies(@Body request: SubspeciesCreateRequest): Response<SubspeciesResponse>

    @PUT("subspecies")
    suspend fun updateSubspecies(@Body subspecies: SubspeciesResponse): SubspeciesResponse

    @DELETE("subspecies/{id}")
    suspend fun deleteSubspeciesById(@Path("id") id: UUID)
}