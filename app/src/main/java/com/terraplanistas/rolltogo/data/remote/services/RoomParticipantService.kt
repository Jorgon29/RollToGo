package com.terraplanistas.rolltogo.data.remote.services
import com.terraplanistas.rolltogo.data.remote.dtos.RoomParticipantCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.RoomParticipantResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface RoomParticipantService {
    @GET("room-participants")
    suspend fun getAllRoomParticipants(): List<RoomParticipantResponse>
    @GET("room-participants/{id}")
    suspend fun getRoomParticipantById(@Path("id") id: UUID): RoomParticipantResponse
    @POST("room-participants")
    suspend fun createRoomParticipant(@Body request: RoomParticipantCreateRequest): Response<RoomParticipantResponse>
    @PUT("room-participants")
    suspend fun updateRoomParticipant(@Body roomParticipant: RoomParticipantResponse): RoomParticipantResponse
    @DELETE("room-participants/{id}")
    suspend fun deleteRoomParticipantById(@Path("id") id: UUID)
}
