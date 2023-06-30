package com.example.radiusagentassignment.Interface

import com.example.radiusagentassignment.FacilityData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {

    @GET("iranjith4/ad-assignment/db")
    fun getData(): Call<FacilityData>

    companion object {
        var BASE_URL = "https://my-json-server.typicode.com/"

        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}