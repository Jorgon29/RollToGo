package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.AbilityScoreImprovementCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.AbilityScoreImprovementResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface AbilityScoreImprovementService {

    @GET("ability_score_improvement")
    suspend fun getAllAbilityScoreImprovements(): List<AbilityScoreImprovementResponse>

    @GET("ability_score_improvement/{id}")
    suspend fun getAbilityScoreImprovementById(@Path("id") id: UUID): AbilityScoreImprovementResponse

    @POST("ability_score_improvement")
    suspend fun createAbilityScoreImprovement(@Body request: AbilityScoreImprovementCreateRequest): Response<AbilityScoreImprovementResponse>

    @PUT("ability_score_improvement")
    suspend fun updateAbilityScoreImprovement(@Body abilityScoreImprovement: AbilityScoreImprovementResponse): AbilityScoreImprovementResponse

    @DELETE("ability_score_improvement/{id}")
    suspend fun deleteAbilityScoreImprovementById(@Path("id") id: UUID)
}