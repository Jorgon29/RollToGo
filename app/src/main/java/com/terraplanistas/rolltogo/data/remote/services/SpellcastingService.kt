package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SpellcastingCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SpellcastingResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SpellcastingService {

    @GET("spellcasting")
    suspend fun getAllSpellcasting(): List<SpellcastingResponse>

    @GET("spellcasting/{id}")
    suspend fun getSpellcastingById(@Path("id") id: UUID): SpellcastingResponse

    @POST("spellcasting")
    suspend fun createSpellcasting(@Body request: SpellcastingCreateRequest): Response<SpellcastingResponse>

    @PUT("spellcasting")
    suspend fun updateSpellcasting(@Body spellcasting: SpellcastingResponse): SpellcastingResponse

    @DELETE("spellcasting/{id}")
    suspend fun deleteSpellcastingById(@Path("id") id: UUID)
}