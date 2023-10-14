package com.example.islami.ApiManger


import com.example.islami.ApiManger.Model.Response
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("radios")
    //"radios/radio_arabic.json"
    fun getRadioChannels():Call<Response>
}