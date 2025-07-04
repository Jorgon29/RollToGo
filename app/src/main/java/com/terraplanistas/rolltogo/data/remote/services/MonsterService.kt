package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.dtos.MonsterCreateRequest
import com.terraplanistas.rolltogo.data.remote.services.MonsterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface MonsterService {

    @GET("monsters")
    suspend fun getAllMonsters(): List<MonsterResponse>

    @GET("monsters/{id}")
    suspend fun getMonsterById(@Path("id") id: UUID): MonsterResponse
    @POST("monsters")
    suspend fun createMonster(@Body request: MonsterCreateRequest): Response<MonsterResponse>
    @PUT("monsters")
    suspend fun updateMonster(@Body monster: MonsterResponse): MonsterResponse

    @DELETE("monsters/{id}")
    suspend fun deleteMonsterById(@Path("id") id: UUID)
}