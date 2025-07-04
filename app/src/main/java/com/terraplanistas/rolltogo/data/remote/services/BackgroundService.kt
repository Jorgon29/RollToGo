package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.BackgroundCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.BackgroundResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface BackgroundService {

    @GET("backgrounds")
    suspend fun getAllBackgrounds(): List<BackgroundResponse>

    @GET("backgrounds/{id}")
    suspend fun getBackgroundById(@Path("id") id: UUID): BackgroundResponse

    @POST("backgrounds")
    suspend fun createBackground(@Body request: BackgroundCreateRequest): Response<BackgroundResponse>

    @PUT("backgrounds")
    suspend fun updateBackground(@Body background: BackgroundResponse): BackgroundResponse

    @DELETE("backgrounds/{id}")
    suspend fun deleteBackgroundById(@Path("id") id: UUID)
}