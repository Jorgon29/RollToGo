package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.RoomCreatureCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.RoomCreatureResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface RoomCreatureService {

    @GET("room-creature")
    suspend fun getAllRoomCreatures(): List<RoomCreatureResponse>

    @GET("room-creature/{id}")
    suspend fun getRoomCreatureById(@Path("id") id: UUID): RoomCreatureResponse

    @POST("room-creature")
    suspend fun createRoomCreature(@Body request: RoomCreatureCreateRequest): Response<RoomCreatureResponse>

    @PUT("room-creature")
    suspend fun updateRoomCreature(@Body roomCreature: RoomCreatureResponse): RoomCreatureResponse

    @DELETE("room-creature/{id}")
    suspend fun deleteRoomCreatureById(@Path("id") id: UUID)
}