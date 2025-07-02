package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.DamageCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.DamageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface DamageService {

    @GET("damage")
    suspend fun getAllDamage(): List<DamageResponse>

    @GET("damage/{id}")
    suspend fun getDamageById(@Path("id") id: UUID): DamageResponse


    @POST("damage")
    suspend fun createDamage(@Body request: DamageCreateRequest): Response<DamageResponse>

    @PUT("damage")
    suspend fun updateDamage(@Body damage: DamageResponse): DamageResponse

    @DELETE("damage/{id}")
    suspend fun deleteDamageById(@Path("id") id: UUID)
}