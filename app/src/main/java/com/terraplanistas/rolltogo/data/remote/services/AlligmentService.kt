package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.services.AlignmentResponse
import com.terraplanistas.rolltogo.data.remote.services.ApiListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlignmentService {

    @GET("alignments")
    suspend fun getAllAlignments(): ApiListResponse<AlignmentResponse>


    @GET("alignments/{index}")
    suspend fun getAlignmentDetails(@Path("index") index: String): AlignmentResponse
}