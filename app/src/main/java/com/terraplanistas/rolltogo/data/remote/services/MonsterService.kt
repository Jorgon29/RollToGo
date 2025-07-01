package com.terraplanistas.rolltogo.data.remote.responses

import com.google.android.gms.common.api.Response
import com.terraplanistas.rolltogo.data.remote.services.ApiListResponse
import com.terraplanistas.rolltogo.data.remote.services.MonsterResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.GET

interface MonsterService {

    @GET("api/monsters")
    suspend fun getApiMonsters(): ApiListResponse<MonsterResponse>

    @GET("api/monsters/{index}")
    suspend fun getApiMonsterDetails(@Path("index") index: String): MonsterResponse

    // Datos locales (personalizados)
    @POST("monsters/custom")
    suspend fun saveCustomMonster(@Body monster: MonsterResponse): MonsterResponse
}