package com.terraplanistas.rolltogo.data.remote.responses

import com.google.android.gms.common.api.Response
import com.terraplanistas.rolltogo.data.remote.services.CharacterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CharacterService {

    @POST("characters")
    suspend fun saveCharacter(@Body character: CharacterResponse): CharacterResponse


    @GET("characters")
    suspend fun getCharacters(): List<CharacterResponse>
}