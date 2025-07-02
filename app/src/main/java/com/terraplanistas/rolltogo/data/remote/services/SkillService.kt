package com.terraplanistas.rolltogo.data.remote.services


import com.terraplanistas.rolltogo.data.remote.dtos.SkillCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.SkillResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface SkillService {

    @GET("skills")
    suspend fun getAllSkills(): List<SkillResponse>

    @GET("skills/{id}")
    suspend fun getSkillById(@Path("id") id: UUID): SkillResponse

    @POST("skills")
    suspend fun createSkill(@Body request: SkillCreateRequest): Response<SkillResponse>

    @PUT("skills")
    suspend fun updateSkill(@Body skill: SkillResponse): SkillResponse

    @DELETE("skills/{id}")
    suspend fun deleteSkillById(@Path("id") id: UUID)
}