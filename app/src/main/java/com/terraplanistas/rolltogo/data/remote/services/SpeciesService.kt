package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SpeciesCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.SubspeciesCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SpeciesResponse
import com.terraplanistas.rolltogo.data.remote.responses.SubspeciesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SpeciesService {
    @GET("species")
    suspend fun getAllSubspecies(): List<SpeciesResponse>

    @GET("species/{id}")
    suspend fun getSpeciesById(@Path("id") id: UUID): SpeciesResponse

    @POST("species")
    suspend fun createSpecies(@Body request: SpeciesCreateRequest): Response<SpeciesResponse>

    @PUT("species")
    suspend fun updateSpecies(@Body subspecies: SpeciesResponse): SpeciesResponse

    @DELETE("species/{id}")
    suspend fun deleteSpeciesById(@Path("id") id: UUID)
}