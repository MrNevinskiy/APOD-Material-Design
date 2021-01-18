package com.hw.apodmaterialdesign.data

import com.hw.apodmaterialdesign.data.entity.APODServerResponseData

sealed class PictureOfTheDayData {
    data class Success(val serverResponseData: APODServerResponseData) : PictureOfTheDayData()
    data class Error(val error: Throwable) : PictureOfTheDayData()
    data class Loading(val progress: Int?) : PictureOfTheDayData()
}