package com.terraplanistas.rolltogo.data.remote.responses

import com.terraplanistas.rolltogo.data.remote.services.GenderResponse
import retrofit2.http.GET

interface GenderService {

    @GET("genders")
    suspend fun getGenders(): List<GenderResponse>
}