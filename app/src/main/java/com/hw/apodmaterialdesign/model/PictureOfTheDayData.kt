package com.hw.apodmaterialdesign.model

import com.hw.apodmaterialdesign.model.entity.APODServerResponseData

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: APODServerResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}