package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.services.PlaystyleResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaystyleService {

    @POST("playstyles")
    suspend fun savePlaystyle(@Body playstyle: PlaystyleResponse): PlaystyleResponse

    @GET("playstyles")
    suspend fun getPlaystyles(): List<PlaystyleResponse>
}