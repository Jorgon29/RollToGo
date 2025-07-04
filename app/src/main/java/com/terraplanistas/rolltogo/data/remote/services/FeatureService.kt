package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.FeatureCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.FeatureResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface FeatureService {

    @GET("features")
    suspend fun getAllFeatures(): List<FeatureResponse>

    @GET("features/{id}")
    suspend fun getFeatureById(@Path("id") id: UUID): FeatureResponse

    @POST("features")
    suspend fun createFeature(@Body request: FeatureCreateRequest): Response<FeatureResponse>


    @PUT("features")
    suspend fun updateFeature(@Body feature: FeatureResponse): FeatureResponse

    @DELETE("features/{id}")
    suspend fun deleteFeatureById(@Path("id") id: UUID)
}
