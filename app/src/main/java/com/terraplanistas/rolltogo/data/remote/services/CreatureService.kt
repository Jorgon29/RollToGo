package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.ContentCreateRequest
import com.terraplanistas.rolltogo.data.remote.dtos.CreatureCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.ContentResponse
import com.terraplanistas.rolltogo.data.remote.responses.CreatureResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface CreatureService {
    @GET("creatures")
    suspend fun getAllCreatures(): List<CreatureResponse>

    @GET("creatures/{id}")
    suspend fun getCreatureById(@Path("id") id: UUID): CreatureResponse

    @PUT("creatures")
    suspend fun updateCreature(@Body content: CreatureResponse): CreatureResponse

    @POST("creatures")
    suspend fun createCreature(@Body request: CreatureCreateRequest): Response<CreatureResponse>

    @DELETE("creatures/{id}")
    suspend fun deleteCreatureById(@Path("id") id: UUID)
}