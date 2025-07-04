package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.MovementCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.MovementResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface MovementService {

    @GET("movements")
    suspend fun getAllMovements(): List<MovementResponse>

    @GET("movements/{id}")
    suspend fun getMovementById(@Path("id") id: UUID): MovementResponse
    @POST("movements")
    suspend fun createMovement(@Body request: MovementCreateRequest): Response<MovementResponse>
    @PUT("movements")
    suspend fun updateMovement(@Body movement: MovementResponse): MovementResponse

    @DELETE("movements/{id}")
    suspend fun deleteMovementById(@Path("id") id: UUID)
}
