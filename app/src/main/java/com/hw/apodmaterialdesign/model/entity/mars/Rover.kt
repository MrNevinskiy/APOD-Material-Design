package com.hw.apodmaterialdesign.model.entity.mars

import com.google.gson.annotations.SerializedName

data class Rover (

	@field:SerializedName("id") val id : Int?,
	@field:SerializedName("name") val name : String?,
	@field:SerializedName("landing_date") val landing_date : String?,
	@field:SerializedName("launch_date") val launch_date : String?,
	@field:SerializedName("status") val status : String?
)