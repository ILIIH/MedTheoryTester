package com.example.medtheorytester.framework

import com.example.domain.model.Riddle
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface APIInterface {
    @GET("gerRiddles")
    suspend fun getRiddles(@Query("selectFrom") selectFrom:Int):List<Riddle>
    @POST("/addRiddle")
    suspend fun createUser(@Body riddle: Riddle)

}