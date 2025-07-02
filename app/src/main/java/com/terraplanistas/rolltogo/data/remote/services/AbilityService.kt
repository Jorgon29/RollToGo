package com.terraplanistas.rolltogo.data.remote.services

import retrofit2.Response
import com.terraplanistas.rolltogo.data.remote.dtos.AbilityCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.AbilityResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface AbilityService {

    @GET("ability")
    suspend fun getAllAbilities(): List<AbilityResponse>

    @GET("ability/{id}")
    suspend fun getAbilityById(@Path("id") id: UUID): AbilityResponse

    @POST("ability")
    suspend fun createAbility(@Body abilityRequest: AbilityCreateRequest): Response<AbilityResponse>

    @PUT("ability")
    suspend fun updateAbility(@Body ability: AbilityResponse): AbilityResponse

    @DELETE("ability/{id}")
    suspend fun deleteAbility(@Path("id") id: UUID)
}