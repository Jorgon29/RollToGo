package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.ActionCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.ActionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface ActionService {

    @GET("actions")
    suspend fun getAllActions(): List<ActionResponse>

    @GET("actions/{id}")
    suspend fun getActionById(@Path("id") id: UUID): ActionResponse

    @POST("actions")
    suspend fun createAction(@Body request: ActionCreateRequest): Response<ActionResponse>

    @PUT("actions")
    suspend fun updateAction(@Body action: ActionResponse): ActionResponse

    @DELETE("actions/{id}")
    suspend fun deleteActionById(@Path("id") id: UUID)
}