package com.example.islami.ApiManger.Model

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("radios")
	val radios: List<RadiosItem?>? = null
)

data class RadiosItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("url")
	val url: String? = null
)
