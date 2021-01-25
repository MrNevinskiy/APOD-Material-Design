package com.hw.apodmaterialdesign.model.api

import com.hw.apodmaterialdesign.model.entity.apod.APODServerResponseData
import com.hw.apodmaterialdesign.model.entity.mars.MarsServerResponseData
import com.hw.apodmaterialdesign.model.entity.solar.SolarFlareServerResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String): Call<APODServerResponseData>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getPictureMars(@Query("earth_date") date: String, @Query("api_key") apiKey: String):Call<MarsServerResponseData>

    @GET("DONKI/FLR")
    fun getSolarFlare(@Query("startDate") startDate: String,@Query("endDate") endDate: String,@Query("api_key") apiKey: String):Call<SolarFlareServerResponseData>
}