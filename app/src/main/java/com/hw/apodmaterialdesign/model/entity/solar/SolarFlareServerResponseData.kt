package com.hw.apodmaterialdesign.model.entity.solar

import com.google.gson.annotations.SerializedName

class SolarFlareServerResponseData (
    @field:SerializedName("flrID") val flrID : String,
    @field:SerializedName("instruments") val instruments : List<Instruments>,
    @field:SerializedName("beginTime") val beginTime : String,
    @field:SerializedName("peakTime") val peakTime : String,
    @field:SerializedName("endTime") val endTime : String,
    @field:SerializedName("classType") val classType : String,
    @field:SerializedName("sourceLocation") val sourceLocation : String,
    @field:SerializedName("activeRegionNum") val activeRegionNum : Int,
    @field:SerializedName("linkedEvents") val linkedEvents : List<LinkedEvents>,
    @field:SerializedName("link") val link : String
)