package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.GrantOptionSetCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.GrantOptionSetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface GrantOptionSetService {

    @GET("grant-option-sets")
    suspend fun getAllGrantOptionSets(): List<GrantOptionSetResponse>

    @GET("grant-option-sets/{id}")
    suspend fun getGrantOptionSetById(@Path("id") id: UUID): GrantOptionSetResponse

    @POST("grant-option-sets")
    suspend fun createGrantOptionSet(@Body request: GrantOptionSetCreateRequest): Response<GrantOptionSetResponse>

    @PUT("grant-option-sets")
    suspend fun updateGrantOptionSet(@Body grantOptionSet: GrantOptionSetResponse): GrantOptionSetResponse

    @DELETE("grant-option-sets/{id}")
    suspend fun deleteGrantOptionSetById(@Path("id") id: UUID)
}