package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.GrantCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.GrantResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface GrantService {

    @GET("grants")
    suspend fun getAllGrants(): List<GrantResponse>

    @GET("grants/{id}")
    suspend fun getGrantById(@Path("id") id: UUID): GrantResponse
    @POST("grants")
    suspend fun createGrant(@Body request: GrantCreateRequest): Response<GrantResponse>

    @PUT("grants")
    suspend fun updateGrant(@Body grant: GrantResponse): GrantResponse

    @DELETE("grants/{id}")
    suspend fun deleteGrantById(@Path("id") id: UUID)
}