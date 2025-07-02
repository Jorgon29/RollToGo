package com.terraplanistas.rolltogo.data.remote.services

import com.terraplanistas.rolltogo.data.remote.dtos.UserCreateRequest
import com.terraplanistas.rolltogo.data.remote.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    @GET("api/user")
    suspend fun getAllUsers(): List<UserResponse>

    @GET("api/user/{id}")
    suspend fun getUserById(@Path("id") id: String): UserResponse

    @POST("api/user")
    suspend fun createUser(@Body request: UserCreateRequest): Response<UserResponse>

    @PUT("api/user")
    suspend fun updateUser(@Body user: UserResponse): UserResponse

    @DELETE("api/user/{id}")
    suspend fun deleteUserById(@Path("id") id: String)
}