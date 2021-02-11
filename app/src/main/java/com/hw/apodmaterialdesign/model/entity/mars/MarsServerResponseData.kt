package com.hw.apodmaterialdesign.model.entity.mars

import com.google.gson.annotations.SerializedName

data class MarsServerResponseData (
	@field:SerializedName("photos") val photos : List<Photos>?
)