package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.LimitedUsageCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.LimitedUsageResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface LimitedUsageService {

    @GET("limited-usage")
    suspend fun getAllLimitedUsage(): List<LimitedUsageResponse>

    @GET("limited-usage/{id}")
    suspend fun getLimitedUsageById(@Path("id") id: UUID): LimitedUsageResponse
    @POST("limited-usage")
    suspend fun createLimitedUsage(@Body request: LimitedUsageCreateRequest): Response<LimitedUsageResponse>

    @PUT("limited-usage")
    suspend fun updateLimitedUsage(@Body limitedUsage: LimitedUsageResponse): LimitedUsageResponse

    @DELETE("limited-usage/{id}")
    suspend fun deleteLimitedUsageById(@Path("id") id: UUID)
}