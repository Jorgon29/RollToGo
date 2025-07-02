package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.LevelProgressionCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.LevelProgressionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface LevelProgressionService {
    @GET("level-progressions")
    suspend fun getAllLevelProgressions(): List<LevelProgressionResponse>

    @GET("level-progressions/{id}")
    suspend fun getLevelProgressionById(@Path("id") id: UUID): LevelProgressionResponse

    @POST("level-progressions")
    suspend fun createLevelProgression(@Body request: LevelProgressionCreateRequest): Response<LevelProgressionResponse>

    @PUT("level-progressions")
    suspend fun updateLevelProgression(@Body levelProgression: LevelProgressionResponse): LevelProgressionResponse

    @DELETE("level-progressions/{id}")
    suspend fun deleteLevelProgressionById(@Path("id") id: UUID)
}