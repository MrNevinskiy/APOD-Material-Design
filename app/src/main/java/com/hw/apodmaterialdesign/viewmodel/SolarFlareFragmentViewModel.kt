package com.hw.apodmaterialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hw.apodmaterialdesign.BuildConfig
import com.hw.apodmaterialdesign.model.MarsData
import com.hw.apodmaterialdesign.model.SolarFlareData
import com.hw.apodmaterialdesign.model.entity.solar.SolarFlareServerResponseData
import com.hw.apodmaterialdesign.model.retrofit.APODRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SolarFlareFragmentViewModel constructor(
    private val liveDataForViewToObserve: MutableLiveData<SolarFlareData> = MutableLiveData(),
    private val retrofitImpl: APODRetrofitImpl = APODRetrofitImpl()
) : ViewModel() {

    fun getData(): LiveData<SolarFlareData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = SolarFlareData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        val start = "2016-01-01"
        val end = "2016-01-30"

        if (apiKey.isBlank()) {
            MarsData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getSolarFlare(start,end,apiKey).enqueue(object :
                Callback<ArrayList<SolarFlareServerResponseData>> {
                override fun onResponse(
                    call: Call<ArrayList<SolarFlareServerResponseData>>,
                    response: Response<ArrayList<SolarFlareServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            SolarFlareData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                SolarFlareData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                SolarFlareData.Error(Throwable(message))
                        }

                    }
                }

                override fun onFailure(call: Call<ArrayList<SolarFlareServerResponseData>>, t: Throwable) {
                    liveDataForViewToObserve.value = SolarFlareData.Error(t)
                }
            })

        }
    }
}