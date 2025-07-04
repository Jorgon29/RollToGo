package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.EffectCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.EffectResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface EffectService {

    @GET("effects")
    suspend fun getAllEffects(): List<EffectResponse>

    @GET("effects/{id}")
    suspend fun getEffectById(@Path("id") id: UUID): EffectResponse

    @POST("effects")
    suspend fun createEffect(@Body request: EffectCreateRequest): Response<EffectResponse>

    @PUT("effects")
    suspend fun updateEffect(@Body effect: EffectResponse): EffectResponse

    @DELETE("effects/{id}")
    suspend fun deleteEffectById(@Path("id") id: UUID)
}