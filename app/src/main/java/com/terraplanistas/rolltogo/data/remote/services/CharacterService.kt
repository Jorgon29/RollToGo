package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.dtos.CharacterCreateRequest
import com.terraplanistas.rolltogo.data.remote.services.CharacterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface CharacterService {

    @GET("characters")
    suspend fun getAllCharacters(): List<CharacterResponse>

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: String): CharacterResponse

    @POST("characters")
    suspend fun createCharacter(@Body request: CharacterCreateRequest): Response<CharacterResponse>
    @PUT("characters")
    suspend fun updateCharacter(@Body character: CharacterResponse): CharacterResponse

    @DELETE("characters/{id}")
    suspend fun deleteCharacterById(@Path("id") id: UUID)
}