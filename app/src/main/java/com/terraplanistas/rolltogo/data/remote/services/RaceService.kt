package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.services.ApiListResponse
import com.terraplanistas.rolltogo.data.remote.services.ApiReference
import com.terraplanistas.rolltogo.data.remote.services.RaceResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RaceService {

    @GET("api/races")
    suspend fun getAllRaces(): ApiListResponse<ApiReference>


    @GET("api/races/{index}")
    suspend fun getRaceDetails(@Path("index") index: String): RaceResponse
}