package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.RoomCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.RoomResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface RoomService {

    @GET("room")
    suspend fun getAllRooms(): List<RoomResponse>

    @GET("room/{id}")
    suspend fun getRoomById(@Path("id") id: UUID): RoomResponse
    @POST("room")
    suspend fun createRoom(@Body request: RoomCreateRequest): Response<RoomResponse>

    @PUT("room")
    suspend fun updateRoom(@Body room: RoomResponse): RoomResponse

    @DELETE("room/{id}")
    suspend fun deleteRoomById(@Path("id") id: UUID)
}