package com.hw.apodmaterialdesign.model.api

import com.hw.apodmaterialdesign.model.entity.APODServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<APODServerResponseData>

    @GET("planetary/apod")
    fun getPictureByDate(@Query("api_key") apiKey: String, @Query("date") date: String):Call<APODServerResponseData>
}