package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.ContentCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.ContentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface ContentService {

    @GET("content")
    suspend fun getAllContent(): List<ContentResponse>

    @GET("content/{id}")
    suspend fun getContentById(@Path("id") id: UUID): ContentResponse

    @PUT("content")
    suspend fun updateContent(@Body content: ContentResponse): ContentResponse

    @POST("content")
    suspend fun createContent(@Body request: ContentCreateRequest): Response<ContentResponse>

    @DELETE("content/{id}")
    suspend fun deleteContentById(@Path("id") id: UUID)
}