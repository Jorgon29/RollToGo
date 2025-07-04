package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.SpecialDieCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SpecialDieResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SpecialDieService {

    @GET("special-dice")
    suspend fun getAllSpecialDice(): List<SpecialDieResponse>

    @GET("special-dice/{id}")
    suspend fun getSpecialDieById(@Path("id") id: UUID): SpecialDieResponse

    @POST("special-dice")
    suspend fun createSpecialDie(@Body request: SpecialDieCreateRequest): Response<SpecialDieResponse>
    @PUT("special-dice")
    suspend fun updateSpecialDie(@Body specialDie: SpecialDieResponse): SpecialDieResponse

    @DELETE("special-dice/{id}")
    suspend fun deleteSpecialDieById(@Path("id") id: UUID)
}