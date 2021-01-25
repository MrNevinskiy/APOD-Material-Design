package com.hw.apodmaterialdesign.model

import com.hw.apodmaterialdesign.model.entity.mars.MarsServerResponseData

sealed class MarsData {
    data class Success(val serverResponseData: MarsServerResponseData) : MarsData()
    data class Error(val error: Throwable) : MarsData()
    data class Loading(val progress: Int?) : MarsData()
}