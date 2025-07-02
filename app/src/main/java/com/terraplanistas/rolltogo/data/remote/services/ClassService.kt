package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.dtos.ClassCreateRequest
import com.terraplanistas.rolltogo.data.remote.services.ApiListResponse
import com.terraplanistas.rolltogo.data.remote.services.ApiReference
import com.terraplanistas.rolltogo.data.remote.services.ClassResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface ClassService {

    @GET("classes")
    suspend fun getAllClasses(): List<ClassResponse>

    @GET("classes/{id}")
    suspend fun getClassById(@Path("id") id: UUID): ClassResponse

    @POST("classes")
    suspend fun createClass(@Body request: ClassCreateRequest): Response<ClassResponse>

    @PUT("classes")
    suspend fun updateClass(@Body clazz: ClassResponse): ClassResponse

    @DELETE("classes/{id}")
    suspend fun deleteClassById(@Path("id") id: UUID)
}
