package com.hw.apodmaterialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hw.apodmaterialdesign.BuildConfig
import com.hw.apodmaterialdesign.model.MarsData
import com.hw.apodmaterialdesign.model.entity.apod.APODServerResponseData
import com.hw.apodmaterialdesign.model.entity.mars.MarsServerResponseData
import com.hw.apodmaterialdesign.model.retrofit.APODRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsFragmentViewModel constructor(
    private val liveDataForViewToObserve: MutableLiveData<MarsData> = MutableLiveData(),
    private val retrofitImpl: APODRetrofitImpl = APODRetrofitImpl()
) : ViewModel() {

    fun getData(): LiveData<MarsData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = MarsData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        val num = 20

        if (apiKey.isBlank()) {
            MarsData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictures(num, apiKey).enqueue(object :
                Callback<List<APODServerResponseData>> {
                override fun onResponse(
                    call: Call<List<APODServerResponseData>>,
                    response: Response<List<APODServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            MarsData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                MarsData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                MarsData.Error(Throwable(message))
                        }

                    }
                }

                override fun onFailure(call: Call<List<APODServerResponseData>>, t: Throwable) {
                    liveDataForViewToObserve.value = MarsData.Error(t)
                }
            })

        }
    }
}