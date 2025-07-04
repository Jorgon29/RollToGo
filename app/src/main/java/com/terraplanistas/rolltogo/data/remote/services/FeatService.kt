package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.FeatCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.FeatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface FeatService {

    @GET("feats")
    suspend fun getAllFeats(): List<FeatResponse>

    @GET("feats/{id}")
    suspend fun getFeatById(@Path("id") id: UUID): FeatResponse

    @POST("feats")
    suspend fun createFeat(@Body request: FeatCreateRequest): Response<FeatResponse>

    @PUT("feats")
    suspend fun updateFeat(@Body feat: FeatResponse): FeatResponse

    @DELETE("feats/{id}")
    suspend fun deleteFeatById(@Path("id") id: UUID)
}