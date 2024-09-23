package com.example.medtheorytester.framework

import com.example.domain.model.Riddle
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("gerRiddles")
    suspend fun getRiddles():List<Riddle>
    @POST("/addRiddle")
    suspend fun createUser(@Body riddle: Riddle)

}