package com.hw.apodmaterialdesign.model

import com.hw.apodmaterialdesign.model.entity.solar.SolarFlareServerResponseData

sealed class SolarFlareData {
    data class Success(val serverResponseData: ArrayList<SolarFlareServerResponseData>) : SolarFlareData()
    data class Error(val error: Throwable) : SolarFlareData()
    data class Loading(val progress: Int?) : SolarFlareData()
}