package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SpellMaterialCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SpellMaterialResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SpellMaterialService {

    @GET("spell-material")
    suspend fun getAllSpellMaterials(): List<SpellMaterialResponse>

    @GET("spell-material/{id}")
    suspend fun getSpellMaterialById(@Path("id") id: UUID): SpellMaterialResponse
    @POST("spell-material")
    suspend fun createSpellMaterial(@Body request: SpellMaterialCreateRequest): Response<SpellMaterialResponse>
    @PUT("spell-material")
    suspend fun updateSpellMaterial(@Body spellMaterial: SpellMaterialResponse): SpellMaterialResponse

    @DELETE("spell-material/{id}")
    suspend fun deleteSpellMaterialById(@Path("id") id: UUID)
}