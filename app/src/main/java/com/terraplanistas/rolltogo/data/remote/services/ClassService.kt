package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.services.ApiListResponse
import com.terraplanistas.rolltogo.data.remote.services.ApiReference
import com.terraplanistas.rolltogo.data.remote.services.ClassResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ClassService {

    @GET("api/classes")
    suspend fun getAllClasses(): ApiListResponse<ApiReference>

    @GET("api/classes/{index}")
    suspend fun getClassDetails(@Path("index") index: String): ClassResponse
}

