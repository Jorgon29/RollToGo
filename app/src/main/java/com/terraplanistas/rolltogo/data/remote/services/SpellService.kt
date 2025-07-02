package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SpellCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SpellResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SpellService {

    @GET("api/spells")
    suspend fun getAllSpells(): List<SpellResponse>

    @GET("api/spells/{id}")
    suspend fun getSpellById(@Path("id") id: UUID): SpellResponse

    @POST("api/spells")
    suspend fun createSpell(@Body request: SpellCreateRequest): Response<SpellResponse>

    @PUT("api/spells")
    suspend fun updateSpell(@Body spell: SpellResponse): SpellResponse

    @DELETE("api/spells/{id}")
    suspend fun deleteSpellById(@Path("id") id: UUID)
}