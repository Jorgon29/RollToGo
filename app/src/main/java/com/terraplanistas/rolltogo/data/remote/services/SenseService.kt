package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SenseCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SenseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SenseService {

    @GET("senses")
    suspend fun getAllSenses(): List<SenseResponse>

    @GET("senses/{id}")
    suspend fun getSenseById(@Path("id") id: UUID): SenseResponse

    @POST("senses")
    suspend fun createSense(@Body request: SenseCreateRequest): Response<SenseResponse>

    @PUT("senses")
    suspend fun updateSense(@Body sense: SenseResponse): SenseResponse

    @DELETE("senses/{id}")
    suspend fun deleteSenseById(@Path("id") id: UUID)
}