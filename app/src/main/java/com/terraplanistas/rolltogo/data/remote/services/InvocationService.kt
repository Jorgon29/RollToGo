package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.InvocationCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.InvocationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface InvocationService {

    @GET("invocations")
    suspend fun getAllInvocations(): List<InvocationResponse>

    @GET("invocations/{id}")
    suspend fun getInvocationById(@Path("id") id: UUID): InvocationResponse

    @POST("invocations")
    suspend fun createInvocation(@Body request: InvocationCreateRequest): Response<InvocationResponse>

    @PUT("invocations")
    suspend fun updateInvocation(@Body invocation: InvocationResponse): InvocationResponse

    @DELETE("invocations/{id}")
    suspend fun deleteInvocationById(@Path("id") id: UUID)
}