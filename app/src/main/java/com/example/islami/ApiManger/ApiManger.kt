package com.example.islami.ApiManger

import com.example.islami.adapter.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManger {
    companion object{
        private var retrofit:Retrofit? = null

        private fun getInstance():Retrofit{
            if(retrofit==null){
                retrofit =Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
        fun getWebService(): WebService{
            return getInstance().create(WebService::class.java)
        }
    }
}