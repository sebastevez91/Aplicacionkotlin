package com.example.prueba1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApiService {
    @GET("v3.1/name/{country}")
    fun getCountryInfo(@Path("country") country: String): Call<List<Country>>
}


