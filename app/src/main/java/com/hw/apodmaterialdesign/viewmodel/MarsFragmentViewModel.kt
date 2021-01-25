package com.hw.apodmaterialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hw.apodmaterialdesign.BuildConfig
import com.hw.apodmaterialdesign.model.MarsData
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
        val date = "2014-02-01"

        if (apiKey.isBlank()) {
            MarsData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getPictureMars(date, apiKey).enqueue(object :
                Callback<MarsServerResponseData> {
                override fun onResponse(
                    call: Call<MarsServerResponseData>,
                    response: Response<MarsServerResponseData>
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

                override fun onFailure(call: Call<MarsServerResponseData>, t: Throwable) {
                    liveDataForViewToObserve.value = MarsData.Error(t)
                }
            })

        }
    }
}