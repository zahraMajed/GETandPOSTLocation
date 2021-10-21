package com.example.getandpostlocation

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIinterface {

    @Headers("Content-Type: application/json")
    @GET("/test/?format=json")
    fun getDate(): Call<List<myData.myDataItem>>?

    @Headers("Content-Type: application/json")
    @POST("/test/?format=json")
    fun postData(@Body info:myData.myDataItem): Call<myData.myDataItem>

}