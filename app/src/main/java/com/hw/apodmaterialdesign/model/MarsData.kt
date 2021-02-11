package com.hw.apodmaterialdesign.model

import com.hw.apodmaterialdesign.model.entity.apod.APODServerResponseData

sealed class MarsData {
    data class Success(val serverResponseData: List<APODServerResponseData>) : MarsData()
    data class Error(val error: Throwable) : MarsData()
    data class Loading(val progress: Int?) : MarsData()
}