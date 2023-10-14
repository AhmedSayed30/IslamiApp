package com.example.islami.ApiManger


import com.example.islami.ApiManger.Model.Response
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("radios")
    fun getRadioChannels():Call<Response>
}