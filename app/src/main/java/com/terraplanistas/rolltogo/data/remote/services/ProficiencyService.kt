package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.ProficiencyCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.ProficiencyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface ProficiencyService {

    @GET("proficiencies")
    suspend fun getAllProficiencies(): List<ProficiencyResponse>
    @GET("proficiencies/{id}")
    suspend fun getProficiencyById(@Path("id") id: UUID): ProficiencyResponse
    @POST("proficiencies")
    suspend fun createProficiency(@Body request: ProficiencyCreateRequest): Response<ProficiencyResponse>
    @PUT("proficiencies")
    suspend fun updateProficiency(@Body proficiency: ProficiencyResponse): ProficiencyResponse
    @DELETE("proficiencies/{id}")
    suspend fun deleteProficiencyById(@Path("id") id: UUID)
}